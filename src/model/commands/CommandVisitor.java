package src.model.commands;

public interface CommandVisitor {
	public void visitMoveCommand( MoveCommand mc );
	public void visitRallyPointCommand( RallyPointCommand mc );
	public void visitNoArgsCommand(NoArgsCommand noArgsCommand);
}
