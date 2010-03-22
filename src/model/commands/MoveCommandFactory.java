package src.model.commands;

import src.model.Player;
import src.model.enums.Direction;
import src.model.instances.Instance;

public class MoveCommandFactory extends CommandFactory {
	public MoveCommandFactory( Player p, String token, int numTicks ) { 
		super( p, token, numTicks );
	}
	public MoveCommandFactory( Player p, String token, int numTicks, boolean isInstant ) { 
		super( p, token, numTicks, isInstant );
	}
	
	protected void doSetInstance( final Instance i )
	{
		// TODO: change this
		for ( final Direction dir : Direction.values() )
			addArgument( new Argument( dir.name() ) {
				public void execute()
				{
					MoveCommand d = makeCommand( i );
					d.addDirection( dir );
					i.addCommandToQueue( d );
				}
			});
	}
	
	public MoveCommand makeCommand(Instance i)
	{
		return new MoveCommand( token(), i, numTicks(), isInstant() );
	}

	@Override
	public String comparable() {
		// TODO Auto-generated method stub
		return meaning();
	}
}