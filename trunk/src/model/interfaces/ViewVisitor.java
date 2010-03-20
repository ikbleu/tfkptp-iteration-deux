package src.model.interfaces;

public interface ViewVisitor {
	public void visitGroup( vGroup v );
	public void visitType( vType v );
	public void visitInstance( vInstance v );
	public void visitCommand( vCommand v );
	public void visitArgument( vArgument v );
}
