package src.model.instances;

import src.model.Player;
import src.model.interfaces.GameTile;
import src.model.interfaces.vUnit;
import src.model.interfaces.InstanceVisitor;

public abstract class Unit extends Instance implements vUnit {
	public Unit( Player p, int id, GameTile g )
	{
		super( p, id, g );
	}
	
	final public void accept( InstanceVisitor iv )
	{
		iv.visitUnit( this );
	}
}
