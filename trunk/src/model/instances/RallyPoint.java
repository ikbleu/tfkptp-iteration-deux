package src.model.instances;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import src.model.Player;
import src.model.commands.CommandFactory;
import src.model.control.Device;
import src.model.control.KeyEventInterpreterBuilder;
import src.model.enums.Direction;
import src.model.interfaces.GameTile;
import src.model.interfaces.HasPlayerVisitor;
import src.model.interfaces.vRallyPoint;
import src.model.interfaces.InstanceVisitor;
import src.model.interfaces.vUnit;
import src.util.Hand;
import src.model.interfaces.InstanceAdapter;

public class RallyPoint extends Instance implements vRallyPoint, InstanceExistenceListener {
	private static final boolean DEBUGGING = true; // TODO: remove
	public RallyPoint( Player p, int id, GameTile g )
	{
		super( p, id, g );
		final Hand< Device > armyHand = p.handFactory().make( Device.class );
		armyDevice = new Device()
		{

			@Override
			public String context() {
				// TODO Auto-generated method stub
				return "Instance"; //really alex? really?
			}

			@Override
			public void direct(KeyEventInterpreterBuilder builder) {
				// TODO Auto-generated method stub
				builder.devices( armyHand.spawnLens() );
			}

			@Override
			public String meaning() {
				// TODO Auto-generated method stub
				return token() + id();
			}
			
		};
		
		entireHand = p.handFactory().make( Device.class );
		entireList = new LinkedList< vUnit >();
		bgHand = p.handFactory().make( Device.class );
		bgList = new LinkedList< vUnit >();
		reinfHand = p.handFactory().make( Device.class );
		reinfList = new LinkedList< vUnit >();
		
		armyHand.add( new Device()
		{
			public String context() {
				return "Type";
			}
			
			public void direct(KeyEventInterpreterBuilder builder) {
				builder.devices( entireHand.spawnLens() );
			}
			
			public String meaning() {
				return "Entire";
			}
		});
		armyHand.add( new Device()
		{
			public String context() {
				return "Type";
			}
			
			public void direct(KeyEventInterpreterBuilder builder) {
				builder.devices( bgHand.spawnLens() );
			}
			
			public String meaning() {
				return "BattleGroup";
			}
		});
		armyHand.add( new Device()
		{
			public String context() {
				return "Type";
			}
			
			public void direct(KeyEventInterpreterBuilder builder) {
				builder.devices( reinfHand.spawnLens() );
			}
			
			public String meaning() {
				return "Reinforcements";
			}
		});
	}
	private Device armyDevice;
	private Hand< Device > entireHand;
	private Hand< Device > bgHand;
	private Hand< Device > reinfHand;
	private List< vUnit > entireList;
	private List< vUnit > bgList;
	private List< vUnit > reinfList;
	
	final public void accept( InstanceVisitor iv )
	{
		iv.visitRallyPoint( this );
	}
	
	public void entered(Instance l) {
		// attack it?
	}
	
	public void exited(Instance l) {
		// stop attacking it?
	}
	
	public String token()
	{
		return "instanceRallyPoint";
	}

	@Override
	public void units( List< vUnit > l ) {
		// TODO Auto-generated method stub
	}

	@Override
	public int workers() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public Direction getFacingDirection()
	{
		throw new RuntimeException("TODO");
	}
	
	public String getCurrentAction()
	{
		throw new RuntimeException("TODO");
	}
	
	public void accept( HasPlayerVisitor v )
	{
		v.visitRallyPoint( this );
	}

	public Device armyDevice() {
		return armyDevice;
	}
	
	public void addUnit( Unit u )
	{
		u.addInstanceExistenceListener( this );
	}

	@Override
	public void delInstance(Instance i) {
		entireHand.remove( i );
		bgHand.remove( i );
		reinfHand.remove( i );
		i.accept( new InstanceAdapter() {
			public void visitUnit( vUnit u )
			{
				entireList.remove( u );
				bgList.remove( u );
				reinfList.remove( u );
			}
		});
		calculateProperties( i );
	}

	@Override
	public void newInstance(Instance i) {
		entireHand.add( i );
		if ( DEBUGGING || location().getDistanceFrom( i.location() ) == 0 )
			bgHand.add( i );
		else
			reinfHand.add( i );
		
		i.accept( new InstanceAdapter() {
			public void visitUnit( vUnit u )
			{
				entireList.add( u );
				if ( DEBUGGING || location().getDistanceFrom( u.location() ) == 0 )
					bgList.add( u );
				else
					reinfList.add( u );
			}
		});
		calculateProperties( i );
	}
	
	private Map< String, CommandFactory > commands = new HashMap< String, CommandFactory >();
	
	private void calculateProperties( Instance i )
	{
		// visibility radius, influence radius, available commands
		System.out.println( "the rally point is recalculating properties" );
		i.accept( new InstanceAdapter() {
			public void visitUnit( vUnit u )
			{
				List< CommandFactory > rpCommands = new LinkedList< CommandFactory >();
				u.rallyCommands( rpCommands );
				for ( CommandFactory f : rpCommands )
				{
					commands.remove( f.token() );
					removeSelectableCommand( f );
				}
			}
		});
		
		for ( vUnit u : bgList )
		{
			System.out.println( "unit in bg" );
			List< CommandFactory > rpCommands = new LinkedList< CommandFactory >();
			u.rallyCommands( rpCommands );
			for ( CommandFactory f : rpCommands )
			{
				commands.put( f.token(), f );
				addSelectableCommand( f );
			}
		}
	}
}
