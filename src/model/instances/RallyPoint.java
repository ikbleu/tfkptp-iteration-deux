package src.model.instances;

import src.model.interfaces.vRallyPoint;
import src.model.interfaces.InstanceVisitor;

public class RallyPoint extends Instance implements vRallyPoint {
	final public void accept( InstanceVisitor iv )
	{
		iv.visitRallyPoint( this );
	}
}
