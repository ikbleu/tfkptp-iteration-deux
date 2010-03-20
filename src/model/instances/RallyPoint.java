package src.model.instances;

import src.model.interfaces.GameTile;
import src.model.interfaces.vRallyPoint;
import src.model.interfaces.InstanceVisitor;

public class RallyPoint extends Instance implements vRallyPoint {
	public RallyPoint( GameTile g )
	{
		super( g );
	}
	
	final public void accept( InstanceVisitor iv )
	{
		iv.visitRallyPoint( this );
	}
	
	public void instanceEntered(Instance i) {
		// attack it?
	}
	
	public void instanceExited(Instance i) {
		// stop attacking it?
	}
	
	public String token()
	{
		return "rallyPoint";
	}
}
