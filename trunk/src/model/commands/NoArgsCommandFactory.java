package src.model.commands;

import src.model.Player;
import src.model.instances.Instance;

public class NoArgsCommandFactory extends CommandFactory {
	public NoArgsCommandFactory( Player p, String token, int numTicks ) { 
		super( p, token, numTicks );
	}
	public NoArgsCommandFactory( Player p, String token, int numTicks, boolean isInstant ) { 
		super( p, token, numTicks, isInstant );
	}
	
	protected void doSetInstance( final Instance i )
	{
		addArgument( new Argument( "NONE" ) {
			public void execute()
			{
				NoArgsCommand d = makeCommand( i );
				i.addCommandToQueue( d );
			}
		});
	}
	
	public NoArgsCommand makeCommand(Instance i)
	{
		return new NoArgsCommand( token(), i, numTicks(), isInstant() );
	}

        public String context()
        {
            return "noArguments";
        }

	@Override
	public String comparable() {
		// TODO Auto-generated method stub
		return meaning();
	}
}