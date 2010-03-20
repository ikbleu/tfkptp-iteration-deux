package src.model.instances;

import src.model.interfaces.vStructure;
import src.model.interfaces.InstanceVisitor;

public abstract class Structure extends Instance implements vStructure {
	final public void accept( InstanceVisitor iv )
	{
		iv.visitStructure( this );
	}
}
