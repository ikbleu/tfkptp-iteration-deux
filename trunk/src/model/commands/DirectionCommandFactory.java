package src.model.commands;

import src.model.Player;
import src.model.enums.Direction;
import src.model.instances.Instance;

public class DirectionCommandFactory extends CommandFactory {
	public DirectionCommandFactory( Player p, String token, int numTicks ) { 
		super( p, token, numTicks );
	}
	
	protected void doSetInstance( final Instance i )
	{
		for ( final Direction dir : Direction.values() )
			addArgument( new Argument( dir.name() ) {
				public void execute()
				{
					DirectionCommand d = makeCommand( i );
					d.setDirection( dir );
					i.executeCommand( d );
				}

				@Override
				public <S> Comparable<S> comparable() {
					// TODO Auto-generated method stub
					return null;
				}
			});
	}
	
	public DirectionCommand makeCommand(Instance i)
	{
		return new DirectionCommand( token(), i, numTicks() );
	}

	@Override
	public <S> Comparable<S> comparable() {
		// TODO Auto-generated method stub
		return null;
	}
}