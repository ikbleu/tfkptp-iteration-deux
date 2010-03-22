package src.model.instances.units;

import src.model.Player;
import src.model.instances.Instance;
import src.model.instances.Unit;
import src.model.interfaces.GameTile;

public class StarterUnit extends Unit {

	public StarterUnit(Player p, int id, GameTile g) {
		super(p, id, g);
		// TODO Auto-generated constructor stub
		modifyStat( "statHealth", Integer.MAX_VALUE ); // =[
	}

	@Override
	public String token() {
		// TODO Auto-generated method stub
		return "instanceStarter";
	}

	@Override
	public void entered(Instance l) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exited(Instance l) {
		// TODO Auto-generated method stub

	}
}
