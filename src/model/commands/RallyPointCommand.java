package src.model.commands;

import src.model.instances.Instance;
import src.model.instances.RallyPoint;

public class RallyPointCommand extends Command {
	public RallyPointCommand(String token, Instance i, int numTicks) {
		super(token, i, numTicks);
	}
	
	public void accept(CommandVisitor c) {
		c.visitRallyPointCommand(this);
	}
	
	public RallyPoint getRallyPoint()
	{
		return null;
	}
}
