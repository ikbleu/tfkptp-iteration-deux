package src.model.instances.structures;

import src.model.Player;
import src.model.instances.Instance;
import src.model.interfaces.GameTile;
import src.util.IntRecycler;

class FortressFactory extends StructureFactory {
	public FortressFactory( Player p )
	{
		super( p );
	}
	
	public Fortress doMakeInstance( GameTile loc ) {
		return new Fortress( player(), rec.next(), loc );
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
