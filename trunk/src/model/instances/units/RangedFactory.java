package src.model.instances.units;

import src.model.Player;
import src.model.interfaces.GameTile;

class RangedFactory extends UnitFactory {
	public RangedFactory( Player p )
	{
		super( p );
	}
	
	public Ranged doMakeInstance( GameTile loc ) {
		// TODO: assign IDs
		return new Ranged( player(), 0, loc );
	}
}
