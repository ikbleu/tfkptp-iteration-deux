package src.model.instances;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import src.model.Player;
import src.model.commands.Command;
import src.model.commands.CommandListener;
import src.model.commands.CommandSender;
import src.model.interfaces.GameTile;
import src.model.interfaces.HealthListener;
import src.model.interfaces.MovementListener;
import src.model.interfaces.StatsListener;
import src.model.interfaces.ViewListener;
import src.model.interfaces.vInstance;
import src.model.interfaces.LocatableVisitor;

public abstract class Instance extends Locatable implements vInstance, CommandSender
{
	public Instance( Player p, int id, GameTile g )
	{
		super( g );
		
		this.id = id;
		player = p;
		
		addMovementListener( new MovementListener()
		{
			public void locationChanged( Locatable l, GameTile prev )
			{
				for ( ViewListener vl : viewListeners )
					vl.locationChanged( Instance.this, prev );
			}
		});
	}
	
	private Player player;
	final public Player player()
	{
		return player;
	}
	
	private int id;
	final public int id()
	{
		return id;
	}
	
	private Map< String, Integer > stats = new HashMap< String, Integer >();
	public void stats( Map< String, Integer > m )
	{
		m.putAll( stats );
	}
	
	final public int getStat( String s )
	{
		return stats.get( s );
	}
	
	final public int health()
	{
		return stats.get( "statHealth" );
	}
	
	final public void takeDamage( int delta )
	{
		modifyStat( "statHealth", -delta );
	}
	
	final protected void setStat( String s, int val )
	{
		stats.put( s, val );
		if ( stats.equals( "statHealth" ) )
		{
			for ( HealthListener hl : healthListeners )
				hl.healthChanged( this );
			for ( ViewListener vl : viewListeners )
				vl.healthChanged( this );
			if ( health() <= 0 )
				destroy();
		}
		else
		{
			for ( StatsListener sl : statsListeners )
				sl.statsChanged( this );
			for ( ViewListener vl : viewListeners )
				vl.statsChanged( this );
			
			if ( s.equals( "statInfluenceRadius" ) )
				setInfluenceRadius( val );
		}
	}
	
	protected void doDestruction()
	{
		for ( InstanceExistenceListener il : ieListeners )
			il.delInstance( this );
	}
	
	final public void modifyStat( String s, int delta )
	{
		setStat( s, getStat( s ) + delta );
	}
	
	private List< StatsListener > statsListeners = new LinkedList< StatsListener >();
	final public void addStatsListener( StatsListener cl )
	{
		statsListeners.add( cl );
	}
	final public void removeStatsListener( StatsListener cl )
	{
		statsListeners.remove( cl );
	}
	
	private List< HealthListener > healthListeners = new LinkedList< HealthListener >();
	final public void addHealthListener( HealthListener cl )
	{
		healthListeners.add( cl );
	}
	final public void removeHealthListener( HealthListener cl )
	{
		healthListeners.remove( cl );
	}
	
	private List< ViewListener > viewListeners = new LinkedList< ViewListener >();
	final public void addViewListener( ViewListener cl )
	{
		viewListeners.add( cl );
	}
	final public void removeViewListener( ViewListener cl )
	{
		viewListeners.remove( cl );
	}
	
	private List< InstanceExistenceListener > ieListeners = new LinkedList< InstanceExistenceListener >();
	final public void addInstanceExistenceListener( InstanceExistenceListener cl )
	{
		ieListeners.add( cl );
		cl.newInstance( this );
	}
	final public void removeInstanceExistenceListener( InstanceExistenceListener cl )
	{
		ieListeners.remove( cl );
	}
	
	private Map< String, List< CommandListener > > commandListeners = new HashMap< String, List< CommandListener > >();
	final public void addCommandListener( String token, CommandListener cl )
	{
		if ( ! commandListeners.containsKey( token ) )
			commandListeners.put( token, new LinkedList< CommandListener >() );
		commandListeners.get( token ).add( cl );
	}
	final public void removeCommandListener( String token, CommandListener cl )
	{
		if ( commandListeners.containsKey( token ) )
			commandListeners.get( token ).remove( cl );
	}
	
	final public void executeCommand( Command c )
	{
		for ( CommandListener cl : commandListeners.get( c.token() ) )
			cl.commandOccurred( c );
		player.executeCommand( c );
	}
	
	final public void accept( LocatableVisitor lv )
	{
		lv.visitInstance( this );
	}
	
	
	
	abstract public String token();
}