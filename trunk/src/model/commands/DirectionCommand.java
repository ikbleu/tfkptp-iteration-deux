package src.model.commands;

import src.model.enums.Direction;
import src.model.instances.Instance;
import src.model.instances.RallyPoint;

public class DirectionCommand extends Command {
	public DirectionCommand(String token, Instance i, int numTicks) {
		super(token, i, numTicks);
	}
	
	public void accept(CommandVisitor c) {
		c.visitDirectionCommand(this);
	}
	
	public Direction getDirection()
	{
		return direction;
	}
	
	public void setRallyPoint( Direction d )
	{
		direction = d;
	}
	private Direction direction;
}
