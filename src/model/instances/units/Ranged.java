package src.model.instances.units;

import src.model.Player;
import src.model.instances.Locatable;
import src.model.instances.Unit;
import src.model.interfaces.GameTile;

class Ranged extends Unit {

	public Ranged( Player p, int id, GameTile g )
	{
		super( p, id, g );
	}
	
	public String token() {
		return "instanceRanged";
	}
	
	@Override
	public void entered(Locatable l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exited(Locatable l) {
		// TODO Auto-generated method stub
		
	}
	
	public void destruct()
	{
		destroy();
	}
}
