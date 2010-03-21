/*
 * file: NormalWorkerGroup.java
 */

package src.model.instances.workergroups;

import src.model.instances.WorkerGroup;

import src.model.interfaces.GameTile;
import src.model.WorkerManager;

import java.util.Map;

/**
 * A group of workers with no specialized capabilities.
 *
 * @author Christopher Dudley
 */
public class NormalWorkerGroup extends WorkerGroup
{
    private boolean staff;

    public NormalWorkerGroup(GameTile location, Map<String, Integer> stats,
            WorkerManager manager, boolean staff)
    {
        super(location, stats, manager);
        this.staff = staff;
    }

    public String token()
    {
        if(staff)
            return "wgStaff";
        else
            return "wgIdle";
    }
}
