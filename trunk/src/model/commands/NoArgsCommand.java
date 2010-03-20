package src.model.commands;

import src.model.instances.Instance;

public class NoArgsCommand extends Command {

	public NoArgsCommand(String token, Instance i, int numTicks) {
		super(token, i, numTicks);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void accept(CommandVisitor c) {
		c.visitNoArgsCommand( this );
	}
}
