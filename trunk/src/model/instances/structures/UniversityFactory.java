package src.model.instances.structures;

import src.model.Player;
import src.model.instances.Instance;
import src.model.interfaces.GameTile;
import src.util.IntRecycler;

class UniversityFactory extends StructureFactory {
	public UniversityFactory( Player p )
	{
		super( p );
	}
	
	public ObservationTower doMakeInstance( GameTile loc ) {
		return new ObservationTower( player(), rec.next(), loc );
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
