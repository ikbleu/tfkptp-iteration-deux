package src.model.instances.units;

import src.model.commands.Command;
import src.model.commands.CommandAdapter;
import src.model.commands.CommandFactory;
import src.model.commands.CommandListener;
import src.model.commands.NoArgsCommand;
import src.model.commands.RallyPointCommand;
import src.model.instances.GeneralUnitManager;
import src.model.instances.Instance;
import src.model.instances.InstanceExistenceListener;
import src.model.instances.Unit;
import src.model.interfaces.GameTile;

public abstract class SpecificUnitManager implements InstanceExistenceListener {
	public SpecificUnitManager(GeneralUnitManager m, UnitFactory f) {
		manager = m;
		factory = f;
	}
	private GeneralUnitManager manager; 
	private UnitFactory factory;
	
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
				u.decommission();
			}
		});
		u.addCommandListener( "cmdPowerUp", new CommandListener() {
			public void commandOccurred( Command c )
			{
				u.powerUp();
			}
		});
		u.addCommandListener( "cmdPowerDown", new CommandListener() {
			public void commandOccurred( Command c )
			{
				u.powerDown();
			}
		});
		u.addCommandListener( "cmdRallyPoint", new CommandListener() {
			public void commandOccurred( Command c )
			{
				c.accept( new CommandAdapter()
				{
					public void visitRallyPointCommand(RallyPointCommand mc) {
						u.addToRallyPoint( mc.getRallyPoint() );
					}
				});
			}
		});
		
		u.addSelectableCommand( new CommandFactory()
		{
			public Command makeCommand( Instance i )
			{
				return new NoArgsCommand( "cmdPowerUp", i, 10 );
			}
		});
		u.addSelectableCommand( new CommandFactory()
		{
			public Command makeCommand( Instance i )
			{
				return new NoArgsCommand( "cmdPowerDown", i, 10 );
			}
		});
		u.addSelectableCommand( new CommandFactory()
		{
			public Command makeCommand( Instance i )
			{
				return new NoArgsCommand( "cmdRallyPoint", i, 10 );
			}
		});

		u.addInstanceExistenceListener( this );
		lastUnit = u; // TODO: remove
		return u;
	}
	
	protected UnitFactory unitFactory()
	{
		return factory;
	}
	
	abstract protected boolean canMakeSpecificUnit();
	
	public void delInstance(Instance i) {
		manager.delInstance( i );
		doDelInstance( i );
	}
	abstract protected void doDelInstance( Instance i );
	
	public void newInstance(Instance i) {
		manager.newInstance( i );
		doNewInstance( i );
	}
	abstract protected void doNewInstance( Instance i );
}
