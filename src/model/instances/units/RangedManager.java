package src.model.instances.units;

import src.model.Player;
import src.model.commands.Command;
import src.model.commands.CommandListener;
import src.model.instances.GeneralUnitManager;
import src.model.instances.Instance;

public class RangedManager extends SpecificUnitManager {
	private static final int MAX_RANGED = 10;
	private Player player;
	
	public RangedManager(Player p, GeneralUnitManager m) {
		super( m, new RangedFactory( p ) );
		player = p;
		
		unitFactory().modDefaultStat( "statAP", 5);
		
		p.addCommandListener( "cmdMakeRanged", new CommandListener() {
			public void commandOccurred(Command c) {
				if ( c.when() == "execute"  && canMakeUnit() )
					makeUnit( c.location() );
			}
		});
		
		p.addCommandListener( "cmdArcherAP1", new CommandListener() {
			public void commandOccurred(Command c) {
				if ( c.when() == "execute" )
					unitFactory().modDefaultStat( "statAP", 5);
			}
		});
	}
	
	protected boolean canMakeSpecificUnit()
	{
		return numRanged < MAX_RANGED;
		// TODO: check resource levels
	}
	
	private int numRanged = 0;
	protected void doDelInstance(Instance i) {
		--numRanged;
	}
	
	protected void doNewInstance( final Instance i ) {
		++numRanged;
		// TODO: subtract resources
		player.addCommandListener( "cmdArcherAP1", new CommandListener() {
			public void commandOccurred( Command c ) {
				if ( c.when() == "execute" )
					i.modifyStat( "statAP", 5 );
			}
		});
	}
}
