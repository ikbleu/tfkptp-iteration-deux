package src.model.instances;

import src.model.interfaces.GameTile;
import src.model.interfaces.vStructure;
import src.model.interfaces.InstanceVisitor;

public abstract class Structure extends Instance implements vStructure {
	public Structure( GameTile g )
	{
		super( g );
	}
	
	final public void accept( InstanceVisitor iv )
	{
		iv.visitStructure( this );
	}
}
