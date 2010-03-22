package src.model.instances.units;

import src.model.Player;
import src.model.instances.Instance;
import src.model.instances.Unit;
import src.model.interfaces.GameTile;

import java.util.Map;

class Explorer extends Unit {

	public Explorer( Player p, int id, GameTile g )
	{
		super( p, id, g );
	}
	
	public String token() {
		return "instanceExplorer";
	}
	
	public void entered(Instance l) {
		// aww, no one cares
	}
	
	public void exited(Instance l) {
		// how pathetic
	}
}
