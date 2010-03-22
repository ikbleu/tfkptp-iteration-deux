package src.model.instances.structures;

import src.model.Player;
import src.model.WorkerManager;
import src.model.instances.Instance;
import src.model.interfaces.GameTile;
import src.util.IntRecycler;

class ObservationTowerFactory extends StructureFactory {

    private WorkerManager wm;

	public ObservationTowerFactory( Player p, WorkerManager wm )
	{
		super( p );
                this.wm = wm;
	}
	
	public ObservationTower doMakeInstance( GameTile loc ) {
		return new ObservationTower( player(), rec.next(), loc, wm );
	}
	
	private IntRecycler rec = new IntRecycler();

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
