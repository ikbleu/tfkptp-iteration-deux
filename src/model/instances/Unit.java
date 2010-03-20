package src.model.instances;

import src.model.interfaces.GameTile;
import src.model.interfaces.vUnit;
import src.model.interfaces.InstanceVisitor;

public abstract class Unit extends Instance implements vUnit {
	public Unit( int id, GameTile g )
	{
		super( id, g );
	}
	
	final public void accept( InstanceVisitor iv )
	{
		iv.visitUnit( this );
	}
}
