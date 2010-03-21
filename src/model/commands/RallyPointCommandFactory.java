package src.model.commands;

import src.model.Player;
import src.model.instances.Instance;

public class RallyPointCommandFactory extends CommandFactory {
	public RallyPointCommandFactory( Player p, String token, int numTicks ) { 
		super( p, token, numTicks );
	}
	
	public void setInstance( final Instance i )
	{
		// add command arguments
	}
	
	public RallyPointCommand makeCommand(Instance i)
	{
		return new RallyPointCommand( token(), i, numTicks() );
	}
}