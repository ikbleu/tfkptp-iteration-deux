package src.model.commands;

import java.util.List;

import src.model.enums.Direction;
import src.model.instances.Instance;

public class MoveCommand extends Command {
	public MoveCommand(String token, Instance i, int numTicks) {
		super(token, i, numTicks);
	}

	public void accept(CommandVisitor c) {
		c.visitMoveCommand( this );
	}
	
	public void addDirection( Direction d )
	{
		
	}
	
	public List< Direction > getDirections()
	{
		return null;
	}
}
