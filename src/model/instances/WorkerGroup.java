/*
 * file: WorkerGroup.java
 */

package src.model.instances;

import src.model.interfaces.GameTile;

/**
 * Manages groups of workers that are assigned to certain tasks.
 *
 * @author Christopher Dudley
 */
public abstract class WorkerGroup extends Locatable
{
    // Number of workers in the group.
    private int numWorkers;
    
    // Number of ticks needed per worker to create a new worker.
    private int breedingRate;

    // Progress made towards making a new worker.
    private int breedingProgress;

    /**
     * Creates a new worker group with no workers in it.
     */
    public WorkerGroup(GameTile location)
    {
        super(location);

        numWorkers = 0;
        breedingRate = 100;
        breedingProgress = 0;
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
        target.addWorkers(numTransfering);
        this.removeWorkers(numTransfering);
    }

    private void addWorkers(int numAdded)
    {
        numWorkers += numAdded;
    }

    private void removeWorkers(int numRemoved)
    {
        numWorkers -= numRemoved;
    }

    /**
     * Tells the current worker group to breed. Once the worker group has
     * finished creating new workers, it will add them to the target group.
     *
     * @param target the group to receive the new workers.
     */
    public void breed(WorkerGroup target)
    {
        int numNewWorkers = 0;

        breedingProgress += numWorkers;

        numNewWorkers = breedingProgress % breedingRate;

        if(numNewWorkers > 0)
            breedingProgress -= numNewWorkers * breedingRate;

        target.addWorkers(numNewWorkers);
    }
}