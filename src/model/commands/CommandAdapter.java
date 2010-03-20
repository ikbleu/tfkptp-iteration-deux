package src.model.commands;

public class CommandAdapter implements CommandVisitor {

	@Override
	public void visitMoveCommand(MoveCommand mc) {

	}

	@Override
	public void visitRallyPointCommand(RallyPointCommand mc) {

	}
	
	public void visitNoArgsCommand(NoArgsCommand noArgsCommand) {
		
	}

}
