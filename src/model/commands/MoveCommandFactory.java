package src.model.commands;

import src.model.Player;
import src.model.instances.Instance;

public class MoveCommandFactory extends CommandFactory {
	public MoveCommandFactory( Player p, String token, int numTicks ) { 
		super( p, token, numTicks );
	}
	
	protected void doSetInstance( final Instance i )
	{
		// add command arguments
	}
	
	public MoveCommand makeCommand(Instance i)
	{
		return new MoveCommand( token(), i, numTicks() );
	}

	@Override
	public <S> Comparable<S> comparable() {
		// TODO Auto-generated method stub
		return null;
	}
}