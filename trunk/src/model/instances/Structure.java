package src.model.instances;

import src.model.Player;
import src.model.interfaces.GameTile;
import src.model.interfaces.vStructure;
import src.model.interfaces.InstanceVisitor;

public abstract class Structure extends Instance implements vStructure {
	public Structure( Player p, int id, GameTile g )
	{
		super( p, id, g );
	}
	
	final public void accept( InstanceVisitor iv )
	{
		iv.visitStructure( this );
	}
}
