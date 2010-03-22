package src.model.instances.units;

import src.model.Player;
import src.model.commands.Command;
import src.model.commands.CommandListener;
import src.model.control.Device;
import src.model.instances.GeneralUnitManager;
import src.model.instances.Instance;
import src.util.Hand;

public class RangedManager extends SpecificUnitManager {
	private static final int MAX_RANGED = 10;
	
	private static final int STAT_BASE_HEALTH = 100;
	private static final int STAT_DELTA_HEALTH = 10;
	private static final int STAT_BASE_INFLRAD = 0;
	//private static final int STAT_DELTA_INFLRAD = 0; // not used
	private static final int STAT_BASE_VISRAD = 1;
	private static final int STAT_DELTA_VISRAD = 1;
	private static final int STAT_BASE_ATKPOW = 6;
	private static final int STAT_DELTA_ATKPOW = 2;
	private static final int STAT_BASE_DEFPOW = 2;
	private static final int STAT_DELTA_DEFPOW = 1;
	private static final int STAT_BASE_ARMOR = 20;
	private static final int STAT_DELTA_ARMOR = 5;
	private static final int STAT_BASE_MVSPD = 3;
	private static final int STAT_DELTA_MVSPD = 1;
	private static final int STAT_BASE_EFCY = 0;
	private static final int STAT_DELTA_EFCY = 1;
	
	private static final int COST_FOOD = 0;
	private static final int COST_ORE = 0;
	private static final int COST_ENERGY = 0;
	
	private Player player;
	
	public RangedManager(Player p, GeneralUnitManager m) {
		super( m, new RangedFactory( p ), p, p.handFactory().make( Device.class ) );
		player = p;
		
		unitFactory().modDefaultStat( "statHealth", STAT_BASE_HEALTH);
		unitFactory().modDefaultStat( "statMaxHealth", STAT_BASE_HEALTH);
		unitFactory().modDefaultStat( "statInfluenceRadius", STAT_BASE_INFLRAD);
		unitFactory().modDefaultStat( "statVisibilityRadius", STAT_BASE_VISRAD);
		unitFactory().modDefaultStat( "statAttackPower", STAT_BASE_ATKPOW);
		unitFactory().modDefaultStat( "statDefensePower", STAT_BASE_DEFPOW);
		unitFactory().modDefaultStat( "statArmor", STAT_BASE_ARMOR);
		unitFactory().modDefaultStat( "statMoveSpeed", STAT_BASE_MVSPD);
		unitFactory().modDefaultStat( "statEfficiency", STAT_BASE_EFCY);
		
		p.addCommandListener( "cmdMakeRanged", new CommandListener() {
			public void commandOccurred(Command c) {
				if ( c.when() == "execute"  && canMakeUnit() )
					makeUnit( c.location() );
			}
		});
		
		p.addCommandListener( "cmdResRangedVisRadius", new CommandListener() {
			public void commandOccurred(Command c) {
				if ( c.when() == "execute" )
					unitFactory().modDefaultStat( "statVisibilityRadius", STAT_DELTA_VISRAD );
			}
		});
		
		p.addCommandListener( "cmdResRangedAtkPow", new CommandListener() {
			public void commandOccurred(Command c) {
				if ( c.when() == "execute" )
					unitFactory().modDefaultStat( "statAttackPower", STAT_DELTA_ATKPOW);
			}
		});
		
		p.addCommandListener( "cmdResRangedDefPow", new CommandListener() {
			public void commandOccurred(Command c) {
				if ( c.when() == "execute" )
					unitFactory().modDefaultStat( "statDefensePower", STAT_DELTA_DEFPOW);
			}
		});
		
		p.addCommandListener( "cmdResRangedArmor", new CommandListener() {
			public void commandOccurred(Command c) {
				if ( c.when() == "execute" )
					unitFactory().modDefaultStat( "statArmor", STAT_DELTA_ARMOR);
			}
		});
		
		p.addCommandListener( "cmdResRangedMovement", new CommandListener() {
			public void commandOccurred(Command c) {
				if ( c.when() == "execute" )
					unitFactory().modDefaultStat( "statMoveSpeed", STAT_DELTA_MVSPD);
			}
		});
		
		p.addCommandListener( "cmdResRangedHealth", new CommandListener() {
			public void commandOccurred(Command c) {
				if ( c.when() == "execute" )
					unitFactory().modDefaultStat( "statMaxHealth", STAT_DELTA_HEALTH);
			}
		});
		
		p.addCommandListener( "cmdResRangedEfficiency", new CommandListener() {
			public void commandOccurred(Command c) {
				if ( c.when() == "execute" )
					unitFactory().modDefaultStat( "statEfficiency", STAT_DELTA_EFCY);
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
		player.addCommandListener( "cmdResRangedVisRadius", new CommandListener() {
			public void commandOccurred(Command c) {
				if ( c.when() == "execute" )
					i.modifyStat( "statVisibilityRadius", STAT_DELTA_VISRAD );
			}
		});
		
		player.addCommandListener( "cmdResRangedAtkPow", new CommandListener() {
			public void commandOccurred(Command c) {
				if ( c.when() == "execute" )
					i.modifyStat( "statAttackPower", STAT_DELTA_ATKPOW);
			}
		});
		
		player.addCommandListener( "cmdResRangedDefPow", new CommandListener() {
			public void commandOccurred(Command c) {
				if ( c.when() == "execute" )
					i.modifyStat( "statDefensePower", STAT_DELTA_DEFPOW);
			}
		});
		
		player.addCommandListener( "cmdResRangedArmor", new CommandListener() {
			public void commandOccurred(Command c) {
				if ( c.when() == "execute" )
					i.modifyStat( "statArmor", STAT_DELTA_ARMOR);
			}
		});
		
		player.addCommandListener( "cmdResRangedMovement", new CommandListener() {
			public void commandOccurred(Command c) {
				if ( c.when() == "execute" )
					i.modifyStat( "statMoveSpeed", STAT_DELTA_MVSPD);
			}
		});
		
		player.addCommandListener( "cmdResRangedHealth", new CommandListener() {
			public void commandOccurred(Command c) {
				if ( c.when() == "execute" )
					i.modifyStat( "statMaxHealth", STAT_DELTA_HEALTH);
			}
		});
		
		player.addCommandListener( "cmdResRangedEfficiency", new CommandListener() {
			public void commandOccurred(Command c) {
				if ( c.when() == "execute" )
					i.modifyStat( "statEfficiency", STAT_DELTA_EFCY);
			}
		});
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
