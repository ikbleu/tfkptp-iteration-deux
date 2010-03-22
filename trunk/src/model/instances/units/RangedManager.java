package src.model.instances.units;

import java.util.HashMap;
import java.util.Map;

import src.model.Player;
import src.model.commands.Command;
import src.model.commands.CommandListener;
import src.model.control.Device;
import src.model.instances.Instance;
import src.util.Hand;

public class RangedManager extends SpecificUnitManager {
	private static final int MAX_RANGED = 10;
	
	private static final Map< String, Integer > BASE_STATS = new HashMap< String, Integer >() {{
		put( "statMaxHealth", 100 );
		put( "statInfluenceRadius", 0 );
		put( "statVisibilityRadius", 1 );
		put( "statAttackPower", 6 );
		put( "statDefensePower", 2 );
		put( "statArmor", 20 );
		put( "statMoveSpeed", 3 );
		put( "statEfficiency", 0 );
	}};
	private static final Map< String, Integer > DELTA_STATS = new HashMap< String, Integer >() {{
		put( "statMaxHealth", 10 );
		put( "statInfluenceRadius", 0 );
		put( "statVisibilityRadius", 1 );
		put( "statAttackPower", 2 );
		put( "statDefensePower", 1 );
		put( "statArmor", 5 );
		put( "statMoveSpeed", 1 );
		put( "statEfficiency", 1 );
	}};
	private static final Map< String, Integer > RESOURCE_COST = new HashMap< String, Integer >() {{
		put( "rscFood", 0 );
		put( "rscMetal", 0 );
		put( "rscEnergy", 0 );
	}};
	
	private Player player;
	
	public RangedManager(Player p, GeneralUnitManager m) {
		super( m, new RangedFactory( p ), p, p.handFactory().make( Device.class ), 
				BASE_STATS, DELTA_STATS, RESOURCE_COST, "Ranged" );
		player = p;
		
		p.addCommandListener( "cmdMakeRanged", new CommandListener() {
			public void commandOccurred(Command c) {
				if ( c.when() == "execute"  && canMakeUnit() )
					makeUnit( c.location() );
			}
		});
	}
	
	protected boolean canMakeSpecificUnit()
	{
		return numRanged < MAX_RANGED;
	}
	
	private int numRanged = 0;
	protected void doDelInstance(Instance i) {
		--numRanged;
	}
	
	protected void doNewInstance( final Instance i ) {
		++numRanged;
	}
	
	public String meaning()
	{
		return "typeRanged";
	}

	@Override
	public String comparable() {
		// TODO Auto-generated method stub
		return meaning();
	}
}
