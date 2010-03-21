/*
 * file: WorkerManager.java
 */

package src.model;

import src.model.interfaces.WorkerGroupFactory;
import src.model.interfaces.GameTile;

import src.model.instances.workergroups.BreedingGroup;
import src.model.instances.workergroups.HarvestingGroup;
import src.model.instances.workergroups.NormalWorkerGroup;

import java.util.Map;
import java.util.HashMap;

/**
 * Manages and creates new groups of workers.
 *
 * @author Christopher Dudley
 */
public class WorkerManager implements WorkerGroupFactory
{
    private Player owner;

    private Map<String, Integer> breedStats;
    private Map<String, Integer> harvestStats;
    private Map<String, Integer> normalStats;

    // Total number of workers that can be in existance.
    private static final int MAX_WORKERS = 100;

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
        harvestStats.put("harvestRate", 1);

        normalStats = new HashMap<String, Integer>();

        normalStats.put("density", MAX_WORKERS);
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
        return new HarvestingGroup(location, harvestStats, this, resourceType);
    }

    /**
     * Creates a new worker group that does not have any special capabilities.
     *
     * @param location the location at which the worker group is being created.
     *
     * @return the new NormalWorkerGroup.
     */
    public NormalWorkerGroup newNormalWorkerGroup(GameTile location, String type)
    {
        return new NormalWorkerGroup(location, normalStats, this, type);
    }
}