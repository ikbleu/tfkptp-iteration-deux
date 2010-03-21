/*
 * file: WorkerGroupFactory.java
 */

package src.model.interfaces;

import src.model.instances.workergroups.BreedingGroup;
import src.model.instances.workergroups.HarvestingGroup;
import src.model.instances.workergroups.NormalWorkerGroup;

/**
 * Interface for an object that can create new worker groups of various types
 * and return them.
 *
 * @author Christopher Dudley
 */
public interface WorkerGroupFactory
{
    /**
     * Creates a new worker group that has the capability of breeding to create
     * more workers.
     *
     * @param location the location at which the worker group is being created.
     * 
     * @return the new BreedingGroup.
     */
    public BreedingGroup newBreedingGroup(GameTile location);

    /**
     * Creates a new worker group that has the capability of harvesting to
     * gather more raw resources.
     *
     * @param location the location at which the worker group is being created.
     *
     * @return the new HarvestingGroup.
     */
    public HarvestingGroup newHarvestingGroup(GameTile location, String resourceType);

    /**
     * Creates a new worker group that does not have any special capabilities.
     *
     * @param location the location at which the worker group is being created.
     * @param staff whether or not the group is staffing a building.
     *
     * @return the new NormalWorkerGroup.
     */
    public NormalWorkerGroup newNormalWorkerGroup(GameTile location, boolean staff);
}
