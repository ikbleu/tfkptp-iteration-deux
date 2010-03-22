package src.model.instances.units;

import src.model.Player;
import src.model.instances.Instance;
import src.model.instances.Unit;
import src.model.interfaces.GameTile;

import java.util.Map;

class Melee extends Unit {

	public Melee( Player p, int id, GameTile g )
	{
		super( p, id, g );
	}
	
	public String token() {
		return "instanceMelee";
	}
	
	public void entered(Instance l) {
		// aww, no one cares
	}
	
	public void exited(Instance l) {
		// how pathetic
	}

    public void sentUpkeep(Map<String, Integer> resources)
    {

    }
}
