/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package src.model;

import src.model.instances.WorkerGroup;
import src.model.interfaces.GameTile;

/**
 *
 *
 * @author Christopher Dudley
 */
public class BreedingGroup extends WorkerGroup
{
    // Number of ticks needed per worker to create a new worker.
    private int breedingRate;

    // Progress made towards making a new worker.
    private int breedingProgress;

    /**
     * Creates a new worker group with the capability of breeding. The default
     * breeding rate is 100 ticks per worker to create a new worker, modifiable
     * by research.
     *
     * @param location the tile the group is located on.
     */
    public BreedingGroup(GameTile location)
    {
        super(location);

        breedingRate = 100;
        breedingProgress = 0;
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

        breedingProgress += this.numWorkers();

        numNewWorkers = breedingProgress % breedingRate;

        if(numNewWorkers > 0)
            breedingProgress -= numNewWorkers * breedingRate;

        this.addWorkers(numNewWorkers);
        this.transferWorkers(target, numNewWorkers);
    }

    public String token()
    {
        return "BreedingGroup";
    }
}
