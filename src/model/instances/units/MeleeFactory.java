package src.model.instances.units;

import src.model.Player;
import src.model.instances.Instance;
import src.model.interfaces.GameTile;
import src.util.IntRecycler;

class MeleeFactory extends UnitFactory {
	public MeleeFactory( Player p )
	{
		super( p );
	}
	
	public Melee doMakeInstance( GameTile loc ) {
		return new Melee( player(), rec.next(), loc );
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
