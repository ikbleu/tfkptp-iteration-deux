package src.model.instances.rallypoints;

import src.model.Player;
import src.model.instances.Instance;
import src.model.instances.InstanceExistenceListener;
import src.model.instances.RallyPoint;
import src.model.interfaces.GameTile;

public class RallyPointFactory implements InstanceExistenceListener {
	public RallyPointFactory( Player p )
	{
		player = p;
	}
	private Player player;

	public RallyPoint makeRallyPoint(GameTile startingLocation) {
		// TODO Auto-generated method stub
		return new RallyPoint( player, 0, startingLocation );
	}

	@Override
	public void delInstance(Instance i) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void newInstance(Instance i) {
		// TODO Auto-generated method stub
		
	}

}
