package src.model.commands;

import src.model.instances.Instance;
import src.model.instances.RallyPoint;

public class RallyPointCommand extends Command {
	public RallyPointCommand(String token, Instance i, int numTicks) {
		super(token, i, numTicks);
	}
	public RallyPointCommand(String token, Instance i, int numTicks, boolean isInstant) {
		super(token, i, numTicks, isInstant);
	}
	
	public void accept(CommandVisitor c) {
		c.visitRallyPointCommand(this);
	}
	
	public RallyPoint getRallyPoint()
	{
		return rallyPoint;
	}
	
	public void setRallyPoint( RallyPoint rp )
	{
		rallyPoint = rp;
	}
	private RallyPoint rallyPoint;
}
