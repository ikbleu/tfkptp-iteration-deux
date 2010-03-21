package src.model.instances.units;

import src.model.Player;
import src.model.instances.Instance;
import src.model.instances.Locatable;
import src.model.instances.Unit;
import src.model.interfaces.GameTile;
import src.model.interfaces.StatsListener;

class Ranged extends Unit {

	public Ranged( Player p, int id, GameTile g )
	{
		super( p, id, g );
	}
	
	public String token() {
		return "instanceRanged";
	}
	
	public void entered(Instance l) {
		// aww, no one cares
	}
	
	public void exited(Instance l) {
		// how pathetic
	}
}
