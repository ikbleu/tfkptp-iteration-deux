package src.model.interfaces;

import src.model.instances.RallyPoint;
import src.model.instances.Structure;
import src.model.instances.Unit;

public class InstanceAdapter implements InstanceVisitor {

	public void visitRallyPoint(RallyPoint r) {}

	public void visitStructure(Structure s) {}

	public void visitUnit(Unit u) {}

}
