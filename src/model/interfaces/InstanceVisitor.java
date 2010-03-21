package src.model.interfaces;

import src.model.instances.RallyPoint;
import src.model.instances.Structure;
import src.model.instances.Unit;

public interface InstanceVisitor 
{
	public void visitUnit( Unit u );
	public void visitStructure( Structure s );
	public void visitRallyPoint( RallyPoint r );
}
