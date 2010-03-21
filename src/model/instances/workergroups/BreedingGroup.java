/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package src.model.instances.workergroups;

import src.model.instances.WorkerGroup;
import src.model.WorkerManager;
import src.model.interfaces.GameTile;

import java.util.Map;

/**
 *
 *
 * @author Christopher Dudley
 */
public class BreedingGroup extends WorkerGroup
{
    // Progress made towards making a new worker.
    private int breedingProgress;

    /**
     * Creates a new worker group with the capability of breeding. The default
     * breeding rate is 100 ticks per worker to create a new worker, modifiable
     * by research.
     *
     * @param location the tile the group is located on.
     */
    public BreedingGroup(GameTile location, Map<String, Integer> stats,
            WorkerManager manager)
    {
        super(location, stats, manager);

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

        numNewWorkers = breedingProgress % getStat("breedingRate");

        if(numNewWorkers > 0)
            breedingProgress -= numNewWorkers * getStat("breedingRate");

        numNewWorkers = Math.min(numNewWorkers, numWorkersLeft());

        this.addWorkers(numNewWorkers);
        this.transferWorkers(target, numNewWorkers);
    }

    /**
     * Returns a string token that denotes the type of the locatable object.
     *
     * @return the string type of the object.
     */
    public String token()
    {
        return "wgBreeding";
    }
}
