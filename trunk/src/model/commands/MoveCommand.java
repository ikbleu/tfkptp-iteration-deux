package src.model.commands;

import src.model.instances.Instance;

public class MoveCommand extends Command {
	public MoveCommand(String token, Instance i, int numTicks) {
		super(token, i, numTicks);
	}

	public void accept(CommandVisitor c) {
		c.visitMoveCommand( this );
	}
}
