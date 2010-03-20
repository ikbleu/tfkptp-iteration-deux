/*
 * file: WorkerGroup.java
 */

package src.model.instances;

import java.util.ArrayList;

import src.model.interfaces.GameTile;
import src.model.interfaces.MovementListener;
import src.model.interfaces.RadiusListener;
import src.model.interfaces.LocatableVisitor;

/**
 * Manages groups of workers that are assigned to certain tasks.
 *
 * @author Christopher Dudley
 */
public abstract class WorkerGroup extends Locatable
{
    // Number of workers in the group.
    private int numWorkers;

    /**
     * Creates a new worker group with no workers in it.
     */
    public WorkerGroup(GameTile location)
    {
        super(location);

        numWorkers = 0;
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

    protected void addWorkers(int numAdded)
    {
        numWorkers += numAdded;
    }

    protected void removeWorkers(int numRemoved)
    {
        numWorkers -= numRemoved;
    }

    public void entered( Locatable i )
    {
        // Should never be called.
    }

    public void exited( Locatable i )
    {
        // Should never be called.
    }

    public void accept( LocatableVisitor lv )
    {
        lv.visitWorkerGroup(this);
    }
}