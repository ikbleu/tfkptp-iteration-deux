package src.model.instances.structures;

import src.model.Player;
import src.model.WorkerManager;
import src.model.instances.Instance;
import src.model.instances.Structure;
import src.model.instances.Unit;
import src.model.interfaces.GameTile;

import src.model.instances.WorkerGroup;
import src.model.instances.workergroups.BreedingGroup;
import src.model.instances.workergroups.HarvestingGroup;
import src.model.instances.workergroups.NormalWorkerGroup;

import java.util.Map;
import java.util.HashMap;

class Capital extends Structure {

        private WorkerManager wm;
        private NormalWorkerGroup staffGroup;
        private BreedingGroup breedingGroup;

        private HashMap<GameTile, HarvestingGroup> harvesters;

	public Capital( Player p, int id, GameTile g, WorkerManager wm )
	{
		super( p, id, g, wm );
                this.wm = wm;

                staffGroup = wm.newNormalWorkerGroup(g, true);
                breedingGroup = wm.newBreedingGroup(g);

                harvesters = new HashMap<GameTile, HarvestingGroup>();

                initCommListeners();
	}

        private void initCommListeners()
        {
            
        }
	
	public String token() {
		return "instanceCapital";
	}
	
	public void entered(Instance l) {
		// aww, no one cares
	}
	
	public void exited(Instance l) {
		// how pathetic
	}
}
