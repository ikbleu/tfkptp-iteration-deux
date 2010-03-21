package src.model.interfaces;

import src.model.instances.RallyPoint;
import src.model.instances.Structure;
import src.model.instances.Unit;
import src.model.instances.WorkerGroup;

public interface HasPlayerVisitor
{
	public void visitUnit(Unit u);
	public void visitStructure(Structure s);
	public void visitRallyPoint(RallyPoint rp);
	public void visitWorkerGroup(WorkerGroup wg);
}
