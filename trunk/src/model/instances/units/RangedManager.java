package src.model.instances.units;

import src.model.Player;
import src.model.instances.Command;
import src.model.instances.CommandListener;
import src.model.instances.GeneralUnitManager;
import src.model.instances.Instance;

class RangedManager extends SpecificUnitManager {
	private static final int MAX_RANGED = 10;
	
	private RangedFactory fact;
	public RangedManager(Player p, GeneralUnitManager m) {
		super( m );
		fact = new RangedFactory( p );
		
		p.addCommandListener( "cmdMakeRanged", new CommandListener(){
			public void commandOccurred( Command c )
			{
				if ( canMakeUnit() )
				{
					Ranged r = fact.makeInstance( c.location() );
					r.addInstanceExistenceListener( RangedManager.this );
				}
			}
		});
	}
	
	public boolean canMakeSpecificUnit()
	{
		return numRanged < MAX_RANGED;
		// TODO: check resource levels
	}
	
	private int numRanged = 0;
	protected void doDelInstance(Instance i) {
		--numRanged;
	}
	
	protected void doNewInstance(Instance i) {
		++numRanged;
		// TODO: subtract resources
	}
}
