package src.model.instances.structures;

import src.model.Player;
import src.model.instances.Instance;
import src.model.interfaces.GameTile;
import src.util.IntRecycler;

class CapitalFactory extends StructureFactory {
	public CapitalFactory( Player p )
	{
		super( p );
	}
	
	public Capital doMakeInstance( GameTile loc ) {
		return new Capital( player(), rec.next(), loc );
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
