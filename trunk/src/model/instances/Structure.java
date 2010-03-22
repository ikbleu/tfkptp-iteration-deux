package src.model.instances;

import java.util.Map;

import src.model.Player;
import src.model.WorkerManager;
import src.model.interfaces.GameTile;
import src.model.interfaces.HasPlayerVisitor;
import src.model.interfaces.vInstanceVisitor;
import src.model.interfaces.vStructure;
import src.model.interfaces.InstanceVisitor;

import src.model.instances.WorkerGroup;
import src.model.instances.workergroups.NormalWorkerGroup;

import java.util.HashMap;

public abstract class Structure extends Instance implements vStructure {

        private WorkerManager wm;

        private NormalWorkerGroup staff;

	public Structure( Player p, int id, GameTile g, WorkerManager wm )
	{
		super( p, id, g );
                this.wm = wm;
                staff = null;
	}
	
	final public void accept( InstanceVisitor iv )
	{
		iv.visitStructure( this );
	}
	
	final public void accept( vInstanceVisitor iv )
	{
		iv.visitStructure( this );
	}
	
	public void accept( HasPlayerVisitor v )
	{
		v.visitStructure( this );
	}
	
	@Override
	public Map<String, Integer> getUpkeep() {
		HashMap<String, Integer> structUpkeep = new HashMap<String, Integer>();
                structUpkeep.put("rscFood", getStat("statUpFood"));
                structUpkeep.put("rscMetal", getStat("statUpMetal"));
                structUpkeep.put("rscEnergy", getStat("statUpEnergy"));

                if(staff != null)
                {
                    Map<String, Integer> staffUpkeep = staff.getUpkeep();

                    for(String type : structUpkeep.keySet())
                    {
                        int curTotal = structUpkeep.get(type);
                        int staffAmt = staffUpkeep.get(type);
                        structUpkeep.put(type, curTotal + staffAmt);
                    }
                }

                return structUpkeep;
	}

	@Override
	public void sentUpkeep(Map<String, Integer> resources) {
		System.out.println("OH SNAP upkeep!");
		
	}

	@Override
	public int workers() {
		if(staff == null)
                    return 0;
                else
                    return staff.numWorkers();
	}

        public void receiveWorkers(WorkerGroup wg, int numWorkers)
        {
            if(staff == null)
                staff = wm.newNormalWorkerGroup(location(), true);

            wg.transferWorkers(staff, numWorkers);
        }

        public void sendWorkers(WorkerGroup wg, int numWorkers)
        {
            staff.transferWorkers(wg, numWorkers);

            if(staff.numWorkers() == 0)
            {
                staff.destroy();
                staff = null;
            }
        }

        public NormalWorkerGroup getStaff()
        {
            return staff;
        }
}
