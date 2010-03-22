package src.model.instances.structures;

import src.model.Player;
import src.model.WorkerManager;
import src.model.instances.Instance;
import src.model.instances.Structure;
import src.model.instances.Unit;
import src.model.interfaces.GameTile;

import java.util.Map;

class PowerPlant extends Structure {

	public PowerPlant( Player p, int id, GameTile g, WorkerManager wm )
	{
		super( p, id, g, wm );
	}
	
	public String token() {
		return "instancePowerPlant";
	}
	
	public void entered(Instance l) {
		// aww, no one cares
	}
	
	public void exited(Instance l) {
		// how pathetic
	}
}
