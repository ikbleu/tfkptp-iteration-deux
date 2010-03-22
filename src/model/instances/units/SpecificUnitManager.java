package src.model.instances.units;

import java.util.Map;

import src.model.Player;
import src.model.commands.Command;
import src.model.commands.CommandAdapter;
import src.model.commands.CommandListener;
import src.model.commands.DirectionCommand;
import src.model.commands.DirectionCommandFactory;
import src.model.commands.MoveCommand;
import src.model.commands.MoveCommandFactory;
import src.model.commands.NoArgsCommand;
import src.model.commands.NoArgsCommandFactory;
import src.model.commands.RallyPointCommand;
import src.model.commands.RallyPointCommandFactory;
import src.model.control.Device;
import src.model.control.KeyEventInterpreterBuilder;
import src.model.instances.GeneralUnitManager;
import src.model.instances.Instance;
import src.model.instances.InstanceExistenceListener;
import src.model.instances.RallyPoint;
import src.model.instances.Unit;
import src.model.interfaces.GameTile;
import src.model.interfaces.InstanceAdapter;
import src.model.interfaces.ViewVisitor;
import src.model.interfaces.vRallyPoint;
import src.model.interfaces.vType;
import src.util.Hand;

public abstract class SpecificUnitManager implements InstanceExistenceListener, Device, vType {
	public SpecificUnitManager(GeneralUnitManager m, UnitFactory f, Player p, Hand< Device > h, 
			Map< String, Integer > baseStats, final Map< String, Integer > deltaStats, Map< String, Integer > resourceCost,
			String researchID ) {
		manager = m;
		factory = f;
		hand = h;
		player = p;
		this.deltaStats = deltaStats;
		this.researchID = researchID;
		this.resourceCost = resourceCost;
		
		for ( Map.Entry< String, Integer > e : baseStats.entrySet() )
			factory.modDefaultStat( e.getKey(), e.getValue() );
		factory.modDefaultStat( "statHealth", baseStats.get( "statMaxHealth" ) );
		
		p.addCommandListener( "cmdRes" + researchID + "VisRadius", new CommandListener() {
			public void commandOccurred(Command c) {
				if ( c.when() == "execute" )
					factory.modDefaultStat( "statVisibilityRadius", deltaStats.get( "statVisibilityRadius" ) );
			}
		});
		
		p.addCommandListener( "cmdRes" + researchID + "AtkPow", new CommandListener() {
			public void commandOccurred(Command c) {
				if ( c.when() == "execute" )
					factory.modDefaultStat( "statAttackPower", deltaStats.get( "statAttackPower" ));
			}
		});
		
		p.addCommandListener( "cmdRes" + researchID + "DefPow", new CommandListener() {
			public void commandOccurred(Command c) {
				if ( c.when() == "execute" )
					factory.modDefaultStat( "statDefensePower", deltaStats.get( "statDefensePower" ));
			}
		});
		
		p.addCommandListener( "cmdRes" + researchID + "Armor", new CommandListener() {
			public void commandOccurred(Command c) {
				if ( c.when() == "execute" )
					factory.modDefaultStat( "statArmor", deltaStats.get( "statArmor" ));
			}
		});
		
		p.addCommandListener( "cmdRes" + researchID + "Movement", new CommandListener() {
			public void commandOccurred(Command c) {
				if ( c.when() == "execute" )
					factory.modDefaultStat( "statMoveSpeed", deltaStats.get( "statMoveSpeed" ));
			}
		});
		
		p.addCommandListener( "cmdRes" + researchID + "Health", new CommandListener() {
			public void commandOccurred(Command c) {
				if ( c.when() == "execute" )
					factory.modDefaultStat( "statMaxHealth", deltaStats.get( "statMaxHealth" ));
			}
		});
		
		p.addCommandListener( "cmdRes" + researchID + "Efficiency", new CommandListener() {
			public void commandOccurred(Command c) {
				if ( c.when() == "execute" )
					factory.modDefaultStat( "statEfficiency", deltaStats.get( "statEfficiency" ));
			}
		});
	}
	private GeneralUnitManager manager; 
	private UnitFactory factory;
	private Hand< Device > hand;
	private Player player;
	private Map< String, Integer > deltaStats;
	private String researchID;
	private Map< String, Integer > resourceCost;
	
	public boolean canMakeUnit()
	{
		// TODO: check resources
		return canMakeSpecificUnit() && manager.canMakeUnit();
	}
	
	public static Unit lastUnit = null; // TODO: remove
	
	// precondition: canMakeUnit() is true
	final public Unit makeUnit( GameTile g )
	{
		// TODO: subtract resources
		final Unit u = factory.makeInstance( g );
		
		u.addCommandListener( "cmdDecommission", new CommandListener() {
			public void commandOccurred( Command c )
			{
				if ( ! c.when().equals( "execute" ) ) return;
				u.decommission();
			}
		});
		u.addCommandListener( "cmdPowerUp", new CommandListener() {
			public void commandOccurred( Command c )
			{
				if ( ! c.when().equals( "execute" ) ) return;
				u.powerUp();
			}
		});
		u.addCommandListener( "cmdPowerDown", new CommandListener() {
			public void commandOccurred( Command c )
			{
				if ( ! c.when().equals( "execute" ) ) return;
				u.powerDown();
			}
		});
		u.addCommandListener( "cmdRallyPoint", new CommandListener() {
			public void commandOccurred( Command c )
			{
				if ( ! c.when().equals( "execute" ) ) return;
				c.accept( new CommandAdapter()
				{
					public void visitRallyPointCommand(RallyPointCommand mc) {
						u.addToRallyPoint( mc.getRallyPoint() );
					}
				});
			}
		});
		u.addCommandListener( "cmdRemoveRallyPoint", new CommandListener() {
			public void commandOccurred( Command c )
			{
				if ( ! c.when().equals( "execute" ) ) return;
				u.removeFromRallyPoint();
			}
		});
		
		u.addSelectableCommand( new NoArgsCommandFactory(player,"cmdPowerUp", 10) );
		u.addSelectableCommand( new NoArgsCommandFactory(player, "cmdPowerDown", 1 ));
		final RallyPointCommandFactory rpcf = new RallyPointCommandFactory(player,"cmdRallyPoint", 0);
		u.addSelectableCommand( rpcf );
		u.addSelectableCommand( new NoArgsCommandFactory(player, "cmdDecommission", 0, true));
		
		u.addRallyPointCommand( new DirectionCommandFactory(player, "cmdAttack", 0));
		u.addRallyPointCommand( new DirectionCommandFactory(player, "cmdDefend", 0));
		u.addRallyPointCommand( new MoveCommandFactory(player, "cmdMove", 0));
		
		final InstanceExistenceListener iel = new InstanceExistenceListener() {
			public void delInstance(Instance i) {
				if ( i == u )
					player.removeInstanceExistenceListener( this );
				else
					i.accept( new InstanceAdapter() {
						public void visitRallyPoint( RallyPoint rp )
						{
							rpcf.setInstance( u );
						}
					});
			}
			public void newInstance(Instance i) {
				i.accept( new InstanceAdapter() {
					public void visitRallyPoint( RallyPoint rp )
					{
						rpcf.setInstance( u );
					}
				});
			}
		};
		player.addInstanceExistenceListener( iel );
		
		u.addInstanceExistenceListener( new InstanceExistenceListener() {
			public void delInstance(Instance i) {
				Instance.removeGlobalInstanceExistenceListener( iel );
			}
			public void newInstance(Instance i) {
				
			}
		});

		player.addCommandListener( "cmdRes" + researchID + "VisRadius", new CommandListener() {
			public void commandOccurred(Command c) {
				if ( c.when() == "execute" )
					u.modifyStat( "statVisibilityRadius", deltaStats.get( "statVisibilityRadius" ) );
			}
		});
		
		player.addCommandListener( "cmdRes" + researchID + "AtkPow", new CommandListener() {
			public void commandOccurred(Command c) {
				if ( c.when() == "execute" )
					u.modifyStat( "statAttackPower", deltaStats.get( "statAttackPower" ));
			}
		});
		
		player.addCommandListener( "cmdRes" + researchID + "DefPow", new CommandListener() {
			public void commandOccurred(Command c) {
				if ( c.when() == "execute" )
					u.modifyStat( "statDefensePower", deltaStats.get( "statDefensePower" ));
			}
		});
		
		player.addCommandListener( "cmdRes" + researchID + "Armor", new CommandListener() {
			public void commandOccurred(Command c) {
				if ( c.when() == "execute" )
					u.modifyStat( "statArmor", deltaStats.get( "statArmor" ));
			}
		});
		
		player.addCommandListener( "cmdRes" + researchID + "Movement", new CommandListener() {
			public void commandOccurred(Command c) {
				if ( c.when() == "execute" )
					u.modifyStat( "statMoveSpeed", deltaStats.get( "statMoveSpeed" ));
			}
		});
		
		player.addCommandListener( "cmdRes" + researchID + "Health", new CommandListener() {
			public void commandOccurred(Command c) {
				if ( c.when() == "execute" )
				{
					u.modifyStat( "statMaxHealth", deltaStats.get( "statMaxHealth" ));
					u.modifyStat( "statHealth", deltaStats.get( "statMaxHealth" ));
				}
			}
		});
		
		player.addCommandListener( "cmdRes" + researchID + "Efficiency", new CommandListener() {
			public void commandOccurred(Command c) {
				if ( c.when() == "execute" )
					u.modifyStat( "statEfficiency", deltaStats.get( "statEfficiency" ));
			}
		});

		u.addInstanceExistenceListener( this );
		u.addInstanceExistenceListener( factory );
		u.addInstanceExistenceListener( manager );
		lastUnit = u; // TODO: remove
		return u;
	}
	
	protected UnitFactory unitFactory()
	{
		return factory;
	}
	
	abstract protected boolean canMakeSpecificUnit();
	
	public void delInstance(Instance i) {
		hand.remove( i );
		doDelInstance( i );
	}
	abstract protected void doDelInstance( Instance i );
	
	public void newInstance(Instance i) {
		hand.add( i );
		doNewInstance( i );
	}
	abstract protected void doNewInstance( Instance i );
	
	final public String context()
	{
		return "Type";
	}
	
	final public void direct(KeyEventInterpreterBuilder builder)
	{
		builder.devices( hand.spawnLens() );
	}
	
	@Override
	public void accept(ViewVisitor v) {
		// TODO Auto-generated method stub
		v.visitType( this );
	}
	
	public String token()
	{
		return meaning();
	}
}
