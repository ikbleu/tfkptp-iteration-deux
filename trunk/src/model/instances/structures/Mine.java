package src.model.instances.structures;

import src.model.Player;
import src.model.instances.Instance;
import src.model.instances.Structure;
import src.model.instances.Unit;
import src.model.interfaces.GameTile;

import java.util.Map;

class Mine extends Structure {

	public Mine( Player p, int id, GameTile g )
	{
		super( p, id, g );
	}
	
	public String token() {
		return "instanceMine";
	}
	
	public void entered(Instance l) {
		// aww, no one cares
	}
	
	public void exited(Instance l) {
		// how pathetic
	}
}
