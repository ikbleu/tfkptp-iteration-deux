package src.model.instances.structures;

import java.util.HashMap;
import java.util.Map;

import src.model.Player;
import src.model.WorkerManager;
import src.model.commands.Command;
import src.model.commands.CommandListener;
import src.model.control.Device;
import src.model.instances.Instance;
import src.model.instances.units.GeneralUnitManager;
import src.model.interfaces.GameTile;
import src.util.Hand;

public class MineManager extends SpecificStructureManager {
	private static final Map< String, Integer > BASE_STATS = new HashMap< String, Integer >() {{
		put( "statMaxHealth", 500 );
		put( "statInfluenceRadius", 0 );
		put( "statVisibilityRadius", 1 );
		put( "statAttackPower", 1 );
		put( "statDefensePower", 1 );
		put( "statArmor", 5 );
		put( "statEfficiency", 0 );
	}};
	private static final Map< String, Integer > DELTA_STATS = new HashMap< String, Integer >() {{
		put( "statMaxHealth", 50 );
		put( "statInfluenceRadius", 0 );
		put( "statVisibilityRadius", 1 );
		put( "statAttackPower", 1 );
		put( "statDefensePower", 1 );
		put( "statArmor", 1 );
		put( "statEfficiency", 1 );
	}};
	private static final Map< String, Integer > RESOURCE_COST = new HashMap< String, Integer >() {{
		put( "rscFood", 0 );
		put( "rscMetal", 0 );
		put( "rscEnergy", 0 );
	}};
	
	private Player player;
	
	public MineManager(Player p, GeneralStructureManager m, WorkerManager wm) {
		super( m, new FortressFactory( p, wm ), p, p.handFactory().make( Device.class ),
				BASE_STATS, DELTA_STATS, RESOURCE_COST, "Mine" );
		player = p;
		
		p.addCommandListener( "cmdBuildMine", new CommandListener() {
			public void commandOccurred(Command c) {
				if ( c.when() == "execute"  && canMakeStructure( c.location() ) )
					makeStructure( c.location() );
			}
		});
	}
	
	protected boolean canMakeSpecificStructure( GameTile g )
	{
		// no specific constraints on structures
		return true;
	}
	
	private int numCapitals = 0;
	protected void doDelInstance(Instance i) {
		--numCapitals;
	}
	
	protected void doNewInstance( final Instance i ) {
		++numCapitals;
	}
	
	public String meaning()
	{
		return "typeMine";
	}

	@Override
	public String comparable() {
		// TODO Auto-generated method stub
		return meaning();
	}
}
