/*
 * file: HarvestingGroup.java
 */

package src.model.instances.workergroups;

import src.model.interfaces.GameTile;
import src.model.WorkerManager;

import src.model.instances.WorkerGroup;

import java.util.Map;

/**
 * Represents a group of workers that are assigned to harvest a certain type
 * of resource on their current tile.
 *
 * @author Christopher Dudley
 */
public class HarvestingGroup extends WorkerGroup
{
    private String resourceType;

    private String token;

    public HarvestingGroup(GameTile location, Map<String, Integer> stats,
            WorkerManager manager, String resourceType)
    {
        super(location, stats, manager);

        this.resourceType = resourceType;

        token = "wg" + resourceType;
    }

    /**
     * Harvests resources on the current tile.
     */
    public void harvest()
    {
        //TODO: code this once I know how player and tiles handle resources.
    }

    public String token()
    {
        return token;
    }
}
