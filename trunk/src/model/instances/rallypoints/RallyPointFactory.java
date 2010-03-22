package src.model.instances.rallypoints;

import src.model.Player;
import src.model.WorkerManager;
import src.model.instances.Instance;
import src.model.instances.InstanceExistenceListener;
import src.model.instances.RallyPoint;
import src.model.interfaces.GameTile;
import src.util.IntRecycler;

public class RallyPointFactory implements InstanceExistenceListener {
	public RallyPointFactory( Player p, WorkerManager wm )
	{
		player = p;
                this.wm = wm;
	}
	private Player player;
        private WorkerManager wm;
	private IntRecycler rec = new IntRecycler();

	public RallyPoint makeRallyPoint(GameTile startingLocation) {
		// TODO Auto-generated method stub
		return new RallyPoint( player, rec.next(), startingLocation, wm );
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
