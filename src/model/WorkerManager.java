/*
 * file: WorkerManager.java
 */

package src.model;

import src.model.interfaces.WorkerGroupFactory;
import src.model.interfaces.GameTile;

import src.model.commands.CommandListener;
import src.model.commands.Command;

import src.model.instances.WorkerGroup;
import src.model.instances.workergroups.BreedingGroup;
import src.model.instances.workergroups.HarvestingGroup;
import src.model.instances.workergroups.NormalWorkerGroup;

import src.model.exceptions.YoureDoingItWrongException;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/**
 * Manages and creates new groups of workers.
 *
 * @author Christopher Dudley
 */
public class WorkerManager implements WorkerGroupFactory
{
    private Player owner;

    private HasPlayerManager hpm = HasPlayerManager.getInstance();

    private Map<String, Integer> breedStats;
    private Map<String, Integer> harvestStats;
    private Map<String, Integer> normalStats;

    // The list of active worker groups (more than 0 workers)
    private List<WorkerGroup> active;

    // The map of groups of workers dedicated to harvesting and their locations.
    private Map<GameTile, HarvestingGroup> harvesters;

    // Total number of workers that can be in existance.
    private static final int MAX_WORKERS = 100;

    // Base stats
    private static final int BASE_BREED_RATE = 100;
    private static final int BASE_DENSITY = 1;
    private static final int BASE_HARVEST_RATE = 10;

    // Stat modifications
    private static final int DELTA_BREED_RATE = -10;
    private static final int DELTA_DENSITY = 1;
    private static final int DELTA_HARVEST_RATE = 1;

    // Total number of workers currently in existance.
    private int totalWorkers;

    public WorkerManager(Player owner)
    {
        this.owner = owner;

        totalWorkers = 0;

        breedStats = new HashMap<String, Integer>();

        breedStats.put("density", MAX_WORKERS);
        breedStats.put("breedingRate", 100);

        harvestStats = new HashMap<String, Integer>();

        harvestStats.put("density", 1);
        harvestStats.put("oreRate", 10);
        harvestStats.put("grainRate", 10);
        harvestStats.put("fuelRate", 10);

        normalStats = new HashMap<String, Integer>();

        normalStats.put("density", MAX_WORKERS);

        active = new ArrayList<WorkerGroup>();
        harvesters = new HashMap<GameTile, HarvestingGroup>();

        initCommListeners();
    }

    private void initCommListeners()
    {
        owner.addCommandListener("cmdResWorkDesnity", new CommandListener() {
            public void commandOccurred(Command com)
            {
                if(com.when().equals("execute"))
                {
                    int prevDensity = harvestStats.get("density");
                    harvestStats.put("density", prevDensity + DELTA_DENSITY);
                }
            }
        });

        owner.addCommandListener("cmdResWorkerBreeding", new CommandListener() {
           public void commandOccurred(Command com)
           {
               if(com.when().equals("execute"))
               {
                   int prevRate = breedStats.get("breedingRate");
                   breedStats.put("breedingRate", prevRate + DELTA_BREED_RATE);
               }
           }
        });

        owner.addCommandListener("cmdResWorkerOre", new CommandListener() {
            public void commandOccurred(Command com)
            {
                if(com.when().equals("execute"))
                {
                    int prevRate = harvestStats.get("oreRate");
                    harvestStats.put("oreRate", prevRate + DELTA_HARVEST_RATE);
                }
            }
        });

        owner.addCommandListener("cmdResWorkerGrain", new CommandListener() {
            public void commandOccurred(Command com)
            {
                if(com.when().equals("execute"))
                {
                    int prevRate = harvestStats.get("grainRate");
                    harvestStats.put("grainRate", prevRate + DELTA_HARVEST_RATE);
                }
            }
        });
        owner.addCommandListener("cmdResWorkerFuel", new CommandListener() {
            public void commandOccurred(Command com)
            {
                if(com.when().equals("execute"))
                {
                    int prevRate = harvestStats.get("fuelRate");
                    harvestStats.put("fuelRate", prevRate + DELTA_HARVEST_RATE);
                }
            }
        });
    }

    /**
     * Flags a worker group as active if it has workers added to it after being
     * empty.
     *
     * @param wg the worker group becoming active.
     */
    public void becomeActive(WorkerGroup wg)
    {
        if(wg.numWorkers() > 0)
        {
            active.add(wg);
            hpm.add(wg.location(), wg);
        }
        else
            throw new RuntimeException("A group with no workers can't be active.");
    }

    /**
     * Flags a worker group as inactive if it loses all of its workers.
     *
     * @param wg the worker group becoming inactive.
     */
    public void becomeInactive(WorkerGroup wg)
    {
        if(wg.numWorkers() == 0)
        {
            active.remove(wg);
            hpm.remove(wg.location(), wg);
        }
        else
            throw new RuntimeException("A group with workers can't be inactive.");
    }

    /**
     * Updates that a certain number of workers were added to a worker group
     * being managed by this manager.
     *
     * @param numWorkers the number of workers added.
     */
    public void workersAdded(int numWorkers)
    {
        totalWorkers += numWorkers;
    }

    /**
     * Updates that a certain number of workers were removed from a worker group
     * being managed by this manager.
     *
     * @param numWorkers the number of workers removed.
     */
    public void workersRemoved(int numWorkers)
    {
        totalWorkers -= numWorkers;
    }

    /**
     * Returns the number of workers that can still be created.
     *
     * @return the number of workers that can still be created.
     */
    public int numWorkersLeft()
    {
        return MAX_WORKERS - totalWorkers;
    }

    public boolean move(WorkerGroup wg, GameTile destination)
    {
        boolean moveSuccessful = false;

        try
        {
            if(hpm.move(wg.location(), destination, wg))
            {
                moveSuccessful = true;
            }
        }
        catch(YoureDoingItWrongException e)
        {
            moveSuccessful = false;
        }

        return moveSuccessful;
    }

    /**
     * Returns the player that owns the worker groups managed by the manager.
     *
     * @return the owning player.
     */
    public Player player()
    {
        return owner;
    }

    /**
     * Creates a new worker group that has the capability of breeding to create
     * more workers.
     *
     * @param location the location at which the worker group is being created.
     *
     * @return the new BreedingGroup.
     */
    public BreedingGroup newBreedingGroup(GameTile location)
    {
        return new BreedingGroup(location, breedStats, this);
    }

    /**
     * Creates a new worker group that has the capability of harvesting to
     * gather more raw resources.
     *
     * @param location the location at which the worker group is being created.
     *
     * @return the new HarvestingGroup.
     */
    public HarvestingGroup newHarvestingGroup(GameTile location, String resourceType)
    {
        HarvestingGroup newHGroup = new HarvestingGroup(location, harvestStats, this, resourceType);
        harvesters.put(location, newHGroup);
        return newHGroup;
    }

    /**
     * Creates a new worker group that does not have any special capabilities.
     *
     * @param location the location at which the worker group is being created.
     * @param staff whether or not the worker group is staffing a building.
     *
     * @return the new NormalWorkerGroup.
     */
    public NormalWorkerGroup newNormalWorkerGroup(GameTile location, boolean staff)
    {
        return new NormalWorkerGroup(location, normalStats, this, staff);
    }
}
