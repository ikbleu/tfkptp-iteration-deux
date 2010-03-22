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
import src.model.interfaces.MovementListener;
import src.model.interfaces.ViewListener;
import src.model.interfaces.ViewVisitor;
import src.model.interfaces.vInstance;
import src.model.interfaces.vInstanceVisitor;
import src.model.interfaces.vRallyPoint;
import src.model.interfaces.InstanceVisitor;
import src.model.interfaces.vType;
import src.model.interfaces.vUnit;
import src.util.Hand;
import src.model.interfaces.InstanceAdapter;

public class RallyPoint extends Instance implements vRallyPoint, InstanceExistenceListener, MovementListener {
	private static final boolean DEBUGGING = true; // TODO: remove
	public RallyPoint( Player p, int id, GameTile g )
	{
		super( p, id, g );
		final Hand< Device > armyHand = p.handFactory().make( Device.class );
		armyDevice = new vInstance()
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

			@Override
			public String comparable() {
				// TODO Auto-generated method stub
				return meaning();
			}
			
			public void accept( ViewVisitor v )
			{
				v.visitInstance( this );
			}

			public void accept(vInstanceVisitor v) {}

			public void addViewListener(ViewListener vl) {}

			public String getCurrentAction() {
				return "cmdNone";
			}
			
			public String token()
			{
				return token();
			}
			
			public int id()
			{
				return id();
			}
		};
		
		entireHand = p.handFactory().make( Device.class );
		entireList = new LinkedList< vUnit >();
		bgHand = p.handFactory().make( Device.class );
		bgList = new LinkedList< vUnit >();
		reinfHand = p.handFactory().make( Device.class );
		reinfList = new LinkedList< vUnit >();
		
		armyHand.add( new vType()
		{
			public String context() {
				return "Type";
			}
			
			public void direct(KeyEventInterpreterBuilder builder) {
				builder.devices( entireHand.spawnLens() );
			}
			
			public String meaning() {
				return "typeEntire";
			}

			@Override
			public String comparable() {
				// TODO Auto-generated method stub
				return meaning();
			}

			@Override
			public String token() {
				// TODO Auto-generated method stub
				return meaning();
			}

			@Override
			public void accept(ViewVisitor v) {
				// TODO Auto-generated method stub
				v.visitType( this );
			}
		});
		armyHand.add( new vType()
		{
			public String context() {
				return "Type";
			}
			
			public void direct(KeyEventInterpreterBuilder builder) {
				builder.devices( bgHand.spawnLens() );
			}
			
			public String meaning() {
				return "typeBG";
			}

			@Override
			public String comparable() {
				// TODO Auto-generated method stub
				return meaning();
			}

			@Override
			public String token() {
				// TODO Auto-generated method stub
				return meaning();
			}

			@Override
			public void accept(ViewVisitor v) {
				// TODO Auto-generated method stub
				v.visitType( this );
			}
		});
		armyHand.add( new vType()
		{
			public String context() {
				return "Type";
			}
			
			public void direct(KeyEventInterpreterBuilder builder) {
				builder.devices( reinfHand.spawnLens() );
			}
			
			public String meaning() {
				return "typeReinf";
			}

			@Override
			public String comparable() {
				// TODO Auto-generated method stub
				return meaning();
			}

			@Override
			public String token() {
				// TODO Auto-generated method stub
				return meaning();
			}

			@Override
			public void accept(ViewVisitor v) {
				// TODO Auto-generated method stub
				v.visitType( this );
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
	
	final public void accept( vInstanceVisitor iv )
	{
		iv.visitRallyPoint( this );
	}
	
	public void entered(Instance i) {
		// attack it?
		i.player();
	}
	
	public void exited(Instance i) {
		// stop attacking it?
	}
	
	public String token()
	{
		return "instanceRallyPoint";
	}

	@Override
	public void units( List< vUnit > l ) {
		l.addAll( entireList );
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
			public void visitUnit( Unit u )
			{
				entireList.remove( u );
				bgList.remove( u );
				reinfList.remove( u );
			}
		});
		calculateProperties( i );
	}
	
	public void removeUnit( Unit u )
	{
		delInstance( u );
	}

	@Override
	public void newInstance(Instance i) {
		i.addMovementListener( this );
		locationChanged( i, null );
	}
	
	public void locationChanged( Instance i, GameTile prev )
	{
		entireHand.add( i );
		if ( DEBUGGING || location().getDistanceFrom( i.location() ) == 0 )
			bgHand.add( i );
		else
			reinfHand.add( i );
		
		i.accept( new InstanceAdapter() {
			public void visitUnit( Unit u )
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
	
	private void calculateProperties( Instance i )
	{
		// visibility radius, move speed, available commands
		System.out.println( "the rally point is recalculating properties" );
		i.accept( new InstanceAdapter() {
			public void visitUnit( Unit u )
			{
				List< CommandFactory > rpCommands = new LinkedList< CommandFactory >();
				u.rallyCommands( rpCommands );
				for ( CommandFactory f : rpCommands )
				{
					removeSelectableCommand( f );
				}
			}
		});
		
		int moveSpeed = 0, visRad = 0;
		for ( vUnit u : bgList )
		{
			System.out.println( "unit in bg" );
			List< CommandFactory > rpCommands = new LinkedList< CommandFactory >();
			u.rallyCommands( rpCommands );
			for ( CommandFactory f : rpCommands )
			{
				System.out.println( "adding command: " + f.token() );
				addSelectableCommand( f );
			}
			
			Map< String, Integer > m = new HashMap< String, Integer >();
			u.stats( m );
			int ms = m.get( "statMoveSpeed" );
			int vr = m.get( "statVisibilityRadius" );
			moveSpeed = moveSpeed == 0 ? ms : Math.min( ms, moveSpeed );
			visRad = visRad == 0 ? vr : Math.max( vr, visRad );
		}
		
		setStat( "statMoveSpeed", moveSpeed );
		setStat( "statVisibilityRadius", visRad );
		
		System.out.printf( "my move speed is %d and vis rad is %d\n", getStat( "statMoveSpeed" ), getStat( "statVisibilityRadius" ) );
	}

	@Override
	public <S> Comparable<S> comparable() {
		// TODO Auto-generated method stub
		return null;
	}
}
