package src.model.commands;

import src.model.Player;
import src.model.enums.Direction;
import src.model.instances.Instance;

public class DirectionCommandFactory extends CommandFactory {
	public DirectionCommandFactory( Player p, String token, int numTicks ) { 
		super( p, token, numTicks );
	}
	public DirectionCommandFactory( Player p, String token, int numTicks, boolean isInstant ) { 
		super( p, token, numTicks, isInstant );
	}
	
	protected void doSetInstance( final Instance i )
	{
		for ( final Direction dir : Direction.values() )
			addArgument( new Argument( dir.name() ) {
				public void execute()
				{
					DirectionCommand d = makeCommand( i );
					d.setDirection( dir );
					i.addCommandToQueue( d );
				}
			});
	}
	
	public DirectionCommand makeCommand(Instance i)
	{
		return new DirectionCommand( token(), i, numTicks(), isInstant() );
	}

	@Override
	public String comparable() {
		// TODO Auto-generated method stub
		return meaning();
	}
}