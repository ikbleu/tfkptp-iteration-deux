/*
 * file: HarvestingGroup.java
 */

package src.model.instances.workergroups;

import src.model.interfaces.GameTile;
import src.model.interfaces.Resource;
import src.model.WorkerManager;
import src.model.ResourceManager;

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
    private ResourceManager rscMan;

    private String resourceType;

    private String token;

    public HarvestingGroup(GameTile location, Map<String, Integer> stats,
            WorkerManager manager, String resourceType, ResourceManager rscMan)
    {
        super(location, stats, manager);
        this.rscMan = rscMan;

        this.resourceType = resourceType;

        token = "unknown";

        if(resourceType != null)
        {
            if(resourceType.equals("rscGrain"))
                token = "wgGrain";
            else if(resourceType.equals("rscOre"))
                token = "wgOre";
            else if(resourceType.equals("rscFuel"))
                token = "wgFuel";
        }
    }

    /**
     * Harvests resources on the current tile.
     */
    public void harvest()
    {
        GameTile location = location();
        Resource res = location.getResource(resourceType);
        int harvestAmt = getStat(resourceType + "Rate") * numWorkers();
        int actHarvest = res.harvest(harvestAmt);

        rscMan.harvested(resourceType, actHarvest);
    }

    public String token()
    {
        return token;
    }
}
