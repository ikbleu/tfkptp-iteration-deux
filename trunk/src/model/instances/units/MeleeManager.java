package src.model.instances.units;

import java.util.HashMap;
import java.util.Map;

import src.model.Player;
import src.model.commands.Command;
import src.model.commands.CommandListener;
import src.model.control.Device;
import src.model.instances.GeneralUnitManager;
import src.model.instances.Instance;
import src.util.Hand;

public class MeleeManager extends SpecificUnitManager {
	private static final int MAX_MELEE = 10;
	
	private static final Map< String, Integer > BASE_STATS = new HashMap< String, Integer >() {{
		put( "statMaxHealth", 150 );
		put( "statInfluenceRadius", 0 );
		put( "statVisibilityRadius", 1 );
		put( "statAttackPower", 5 );
		put( "statDefensePower", 2 );
		put( "statArmor", 30 );
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
	
	public MeleeManager(Player p, GeneralUnitManager m) {
		super( m, new MeleeFactory( p ), p, p.handFactory().make( Device.class ), 
				BASE_STATS, DELTA_STATS, RESOURCE_COST, "Melee" );
		player = p;
		
		p.addCommandListener( "cmdMakeMelee", new CommandListener() {
			public void commandOccurred(Command c) {
				if ( c.when() == "execute"  && canMakeUnit() )
					makeUnit( c.location() );
			}
		});
	}
	
	protected boolean canMakeSpecificUnit()
	{
		return numMelee < MAX_MELEE;
	}
	
	private int numMelee = 0;
	protected void doDelInstance(Instance i) {
		--numMelee;
	}
	
	protected void doNewInstance( final Instance i ) {
		++numMelee;
	}
	
	public String meaning()
	{
		return "typeMelee";
	}

	@Override
	public String comparable() {
		// TODO Auto-generated method stub
		return meaning();
	}
}
