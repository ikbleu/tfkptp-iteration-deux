package src.model.instances.structures;

import src.model.Player;
import src.model.WorkerManager;
import src.model.instances.Instance;
import src.model.interfaces.GameTile;
import src.util.IntRecycler;

class PowerPlantFactory extends StructureFactory {

    private WorkerManager wm;

	public PowerPlantFactory( Player p , WorkerManager wm)
	{
		super( p );
                this.wm = wm;
	}
	
	public PowerPlant doMakeInstance( GameTile loc ) {
		return new PowerPlant( player(), rec.next(), loc, wm );
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
