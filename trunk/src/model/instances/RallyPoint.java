package src.model.instances;

import java.util.List;

import src.model.Player;
import src.model.control.Device;
import src.model.control.KeyEventInterpreterBuilder;
import src.model.enums.Direction;
import src.model.interfaces.GameTile;
import src.model.interfaces.HasPlayerVisitor;
import src.model.interfaces.vRallyPoint;
import src.model.interfaces.InstanceVisitor;
import src.model.interfaces.vUnit;
import src.util.Hand;

public class RallyPoint extends Instance implements vRallyPoint, InstanceExistenceListener {
	private static final boolean DEBUGGING = true; // TODO: remove
	public RallyPoint( Player p, int id, GameTile g )
	{
		super( p, id, g );
		final Hand< Device > hand = p.handFactory().make( Device.class );
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
				builder.devices( hand.spawnLens() );
			}

			@Override
			public String meaning() {
				// TODO Auto-generated method stub
				return token() + id();
			}
			
		};
		
		entireHand = p.handFactory().make( Device.class );
		bgHand = p.handFactory().make( Device.class );
		reinfHand = p.handFactory().make( Device.class );
		
		hand.add( new Device()
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
		hand.add( new Device()
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
		hand.add( new Device()
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
		calculateProperties();
	}

	@Override
	public void newInstance(Instance i) {
		entireHand.add( i );
		if ( DEBUGGING || location().getDistanceFrom( i.location() ) == 0 )
			bgHand.add( i );
		else
			reinfHand.add( i );
		calculateProperties();
	}
	
	private void calculateProperties()
	{
		// visibility radius, influence radius, available commands
		System.out.println( "the rally point is recalculating properties" );
	}
}
