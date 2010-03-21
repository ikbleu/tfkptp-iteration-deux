package src.model.instances.units;

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
import src.model.instances.Unit;
import src.model.interfaces.GameTile;
import src.util.Hand;

public abstract class SpecificUnitManager implements InstanceExistenceListener, Device {
	public SpecificUnitManager(GeneralUnitManager m, UnitFactory f, Player p, Hand< Device > h ) {
		manager = m;
		factory = f;
		hand = h;
		player = p;
	}
	private GeneralUnitManager manager; 
	private UnitFactory factory;
	private Hand< Device > hand;
	private Player player;
	
	public boolean canMakeUnit()
	{
		return canMakeSpecificUnit() && manager.canMakeUnit();
	}
	
	public static Unit lastUnit = null; // TODO: remove
	
	// precondition: canMakeUnit() is true
	final public Unit makeUnit( GameTile g )
	{
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
		u.addSelectableCommand( new RallyPointCommandFactory(player,"cmdRallyPoint", 0));
		u.addSelectableCommand( new NoArgsCommandFactory(player, "cmdDecommission", 0));
		
		u.addRallyPointCommand( new DirectionCommandFactory(player, "cmdAttack", 0));
		u.addRallyPointCommand( new DirectionCommandFactory(player, "cmdDefend", 0));
		u.addRallyPointCommand( new MoveCommandFactory(player, "cmdMove", 0));

		u.addInstanceExistenceListener( this );
		u.addInstanceExistenceListener( factory );
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
		manager.delInstance( i );
		doDelInstance( i );
	}
	abstract protected void doDelInstance( Instance i );
	
	public void newInstance(Instance i) {
		hand.add( i );
		manager.newInstance( i );
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
}
