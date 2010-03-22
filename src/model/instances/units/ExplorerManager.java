package src.model.instances.units;

import java.util.HashMap;
import java.util.Map;

import src.model.Player;
import src.model.commands.Command;
import src.model.commands.CommandListener;
import src.model.control.Device;
import src.model.instances.GeneralUnitManager;
import src.model.instances.Instance;
import src.model.interfaces.GameTile;
import src.model.interfaces.MovementListener;
import src.util.Hand;

public class ExplorerManager extends SpecificUnitManager {
	private static final int MAX_EXPLORERS = 10;
	
	private static final Map< String, Integer > BASE_STATS = new HashMap< String, Integer >() {{
		put( "statMaxHealth", 50 );
		put( "statInfluenceRadius", 0 );
		put( "statVisibilityRadius", 3 );
		put( "statAttackPower", 1 );
		put( "statDefensePower", 1 );
		put( "statArmor", 5 );
		put( "statMoveSpeed", 1 );
		put( "statEfficiency", 0 );
	}};
	private static final Map< String, Integer > DELTA_STATS = new HashMap< String, Integer >() {{
		put( "statMaxHealth", 10 );
		put( "statInfluenceRadius", 0 );
		put( "statVisibilityRadius", 1 );
		put( "statAttackPower", 1 );
		put( "statDefensePower", 1 );
		put( "statArmor", 1 );
		put( "statMoveSpeed", 1 );
		put( "statEfficiency", 1 );
	}};
	private static final Map< String, Integer > RESOURCE_COST = new HashMap< String, Integer >() {{
		put( "rscFood", 0 );
		put( "rscMetal", 0 );
		put( "rscEnergy", 0 );
	}};
	
	private Player player;
	
	public ExplorerManager(Player p, GeneralUnitManager m) {
		super( m, new ExplorerFactory( p ), p, p.handFactory().make( Device.class ), 
				BASE_STATS, DELTA_STATS, RESOURCE_COST, "Explorer" );
		player = p;
		
		p.addCommandListener( "cmdMakeExplorer", new CommandListener() {
			public void commandOccurred(Command c) {
				if ( c.when() == "execute"  && canMakeUnit() )
					makeUnit( c.location() );
			}
		});
	}
	
	protected boolean canMakeSpecificUnit()
	{
		return numExplorers < MAX_EXPLORERS;
	}
	
	private int numExplorers = 0;
	protected void doDelInstance(Instance i) {
		--numExplorers;
	}
	
	protected void doNewInstance( final Instance i ) {
		++numExplorers;
	}
	
	public String meaning()
	{
		return "typeExplorer";
	}

	@Override
	public String comparable() {
		// TODO Auto-generated method stub
		return meaning();
	}
}
