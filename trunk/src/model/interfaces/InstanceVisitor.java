package src.model.interfaces;

public interface InstanceVisitor 
{
	public void visitUnit( vUnit u );
	public void visitStructure( vStructure s );
	public void visitRallyPoint( vRallyPoint r );
}
