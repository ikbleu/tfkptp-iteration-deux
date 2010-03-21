package src.model.instances.rallypoints;

import src.model.Player;
import src.model.instances.Instance;
import src.model.instances.InstanceExistenceListener;
import src.model.instances.RallyPoint;
import src.model.interfaces.GameTile;
import src.util.IntRecycler;

public class RallyPointFactory implements InstanceExistenceListener {
	public RallyPointFactory( Player p )
	{
		player = p;
	}
	private Player player;
	private IntRecycler rec = new IntRecycler();

	public RallyPoint makeRallyPoint(GameTile startingLocation) {
		// TODO Auto-generated method stub
		return new RallyPoint( player, rec.next(), startingLocation );
	}

	@Override
	public void delInstance(Instance i) {
		// TODO Auto-generated method stub
		rec.free( i.id() );
	}

	@Override
	public void newInstance(Instance i) {
		// TODO Auto-generated method stub
		
	}

}
