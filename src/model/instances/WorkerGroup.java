/*
 * file: WorkerGroup.java
 */

package src.model.instances;

import src.model.Player;

import src.model.interfaces.GameTile;
import src.model.interfaces.LocatableVisitor;
import src.model.interfaces.HasPlayer;
import src.model.interfaces.HasPlayerVisitor;

import java.util.Map;
import java.util.HashMap;

import src.model.WorkerManager;

/**
 * Manages groups of workers that are assigned to certain tasks.
 *
 * @author Christopher Dudley
 */
public abstract class WorkerGroup extends Locatable implements HasPlayer
{
    // Number of workers in the group.
    private int numWorkers;

    // Contains various stats shared among worker groups.
    private Map<String, Integer> stats;

    // This worker group's manager.
    private WorkerManager manager;

    /**
     * Creates a new worker group with no workers in it.
     *
     * @param location whre the worker group is located.
     * @param stats the stats this group shares with other groups.
     */
    public WorkerGroup(GameTile location, Map<String, Integer> stats,
            WorkerManager manager)
    {
        this(location, stats, manager, 0);
    }

    public WorkerGroup(GameTile location, Map<String, Integer> stats,
            WorkerManager manager, int numWorkers)
    {
        super(location);
        this.stats = stats;
        this.manager = manager;
        numWorkers = 0;
        addWorkers(numWorkers);
    }

    /**
     * Returns the number of workers in the group.
     *
     * @return the number of workers in the group.
     */
    public int numWorkers()
    {
        return numWorkers;
    }

    /**
     * Transfers the specified number of workers from this group to the target
     * WorkerGroup or the number of workers in the group if the desired number
     * to transfer is more than the number of workers currently in this group.
     *
     * @param target the target group to transfer workers to.
     * @param numToTransfer the number of units desired to transfer.
     */
    public void transferWorkers(WorkerGroup target, int numToTransfer)
    {
        int numTransfering = Math.min(numToTransfer, numWorkers);
        int spaceLeft = target.stats.get("density") - target.numWorkers;
        numTransfering = Math.min(spaceLeft, numTransfering);

        this.removeWorkers(numTransfering);
        target.addWorkers(numTransfering);
    }

    /**
     * Adds the specified number of workers to the group.
     *
     * @param numAdded the number of workers to add.
     */
    protected void addWorkers(int numAdded)
    {
        boolean inactive = (numWorkers == 0);

        numWorkers += numAdded;

        if(inactive && numAdded > 0)
            manager.becomeActive(this);
        
        manager.workersAdded(numAdded);
    }

    /**
     * Removes the specified number of workers to the group.
     *
     * @param numRemoved the number of workers to remove.
     */
    protected void removeWorkers(int numRemoved)
    {
        boolean active = (numWorkers > 0);

        numWorkers -= numRemoved;

        if(active && numWorkers == 0)
            manager.becomeInactive(this);

        manager.workersRemoved(numRemoved);
    }

    /**
     * Returns the number of workers that can still be created.
     *
     * @return the number of workers that can still be created.
     */
    protected int numWorkersLeft()
    {
        return manager.numWorkersLeft();
    }

    /**
     * Returns the stat associated with the specified token.
     *
     * @param token the token for the stat.
     * @return the integer stat.
     */
    protected int getStat(String token)
    {
        return stats.get(token);
    }

    /**
     * Returns the player that owns the worker group.
     *
     * @return the player that owns the worker group.
     */
    public Player getPlayer()
    {
        return manager.player();
    }

    /**
     * Determines if the specified object with a player associated with it has
     * the same player as the current worker group.
     *
     * @param hs the object with which to compare players.
     *
     * @return true if the players are the same, false otherwise.
     */
    public boolean hasSamePlayer(HasPlayer hs)
    {
        if(getPlayer().equals(hs.getPlayer()))
            return true;
        else
            return false;
    }

    /**
     * Workers don't care when other objects are within their influence radius,
     * so this does nothing.
     *
     * @param i the instance object that has entered the worker's influence radius.
     */
    public void entered( Instance i )
    {
        // Workers don't care what enters their influence radius
    }

    /**
     * Workers don't care when other objects are within their influence radius,
     * so this does nothing.
     *
     * @param i the instance object that has entered the worker's influence radius.
     */
    public void exited( Instance i )
    {
        // Workers don't care about what exits their influence radius
    }

    public boolean move( GameTile newLoc )
    {
        if(numWorkers == 0)
            return false;

        if(manager.move(this, newLoc))
        {
            this.setLocation(newLoc);
            return true;
        }
        else
        {
            return false;
        }
    }

    public void accept( LocatableVisitor lv )
    {
        lv.visitWorkerGroup(this);
    }

    public void accept(HasPlayerVisitor hpv)
    {
        hpv.visitWorkerGroup(this);
    }

    @Override
    public void doDestruction()
    {
        manager.workersRemoved(numWorkers);
    }

    public Map<String, Integer> getUpkeep()
    {
        HashMap<String, Integer> upkeep = new HashMap<String, Integer>();
        upkeep.put("rscFood", getStat("statUpFood") * numWorkers);
        upkeep.put("rscMetal", getStat("statUpMetal") * numWorkers);
        upkeep.put("rscEnergy", getStat("statUpEnergy") * numWorkers);

        return upkeep;
    }

    public void sentUpkeep(Map<String, Integer> resources)
    {
        int rscNeeded = (getStat("statUpFood") + getStat("statUpMetal") +
                getStat("statUpEnergy")) * numWorkers;
        int rscReceived = resources.get("rscFood") + resources.get("rscMetal") +
                resources.get("rscEnergy");

        if(rscNeeded > rscReceived)
        {
            double lifeRatio = ((double) rscReceived) / ((double) rscNeeded);
            int numToDie = (int) (numWorkers * (1d - lifeRatio));
            numToDie = Math.max(numToDie, 0);

            removeWorkers(numToDie);
        }
    }
}