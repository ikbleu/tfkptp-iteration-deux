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

    // List of things that care if this worker group moves.
    private ArrayList<MovementListener> movementListeners;

    /**
     * Creates a new worker group with no workers in it.
     */
    public WorkerGroup(GameTile location)
    {
        super(location);

        numWorkers = 0;
        movementListeners = new ArrayList<MovementListener>();
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

    /**
     * Worker groups only ever care about the tile they are currently on.
     *
     * @return 0 always.
     */
    public int influenceRadius()
    {
        return 0;
    }

    /**
     * Adds a movement listener to the worker group's list of movement
     * listeners to signal when the group moves.
     *
     * @param ml the movement listener to add.
     */
    final public void addMovementListener(MovementListener ml)
    {
        movementListeners.add(ml);
    }

    protected void updateLocation( GameTile prev )
    {
        for(MovementListener ml : movementListeners)
            ml.instanceMoved(this, prev);
    }

    public void addRadiusListener( RadiusListener rl )
    {
        // Do nothing
    }

    public void instanceEntered( Instance i )
    {
        // Should never be called.
    }

    public void instanceExited( Instance i )
    {
        // Should never be called.
    }

    public void accept( LocatableVisitor lv )
    {
        lv.visitWorkerGroup(this);
    }
}