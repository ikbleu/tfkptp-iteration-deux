package src.model.commands;

import src.model.Player;
import src.model.instances.Instance;

public class NoArgsCommandFactory extends CommandFactory {
	public NoArgsCommandFactory( Player p, String token, int numTicks ) { 
		super( p, token, numTicks );
	}
	
	public void setInstance( final Instance i )
	{
		// add command arguments
	}
	
	public NoArgsCommand makeCommand(Instance i)
	{
		return new NoArgsCommand( token(), i, numTicks() );
	}
}