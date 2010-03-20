package src.model.instances;

import src.model.interfaces.vUnit;
import src.model.interfaces.InstanceVisitor;

public abstract class Unit extends Instance implements vUnit {
	final public void accept( InstanceVisitor iv )
	{
		iv.visitUnit( this );
	}
}
