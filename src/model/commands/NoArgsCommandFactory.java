package src.model.commands;

import src.model.Player;
import src.model.instances.Instance;

public class NoArgsCommandFactory extends CommandFactory {
	public NoArgsCommandFactory( Player p, String token, int numTicks ) { 
		super( p, token, numTicks );
	}
	
	protected void doSetInstance( final Instance i )
	{
		// no arguments
	}
	
	public NoArgsCommand makeCommand(Instance i)
	{
		return new NoArgsCommand( token(), i, numTicks() );
	}

	@Override
	public String comparable() {
		// TODO Auto-generated method stub
		return meaning();
	}
}