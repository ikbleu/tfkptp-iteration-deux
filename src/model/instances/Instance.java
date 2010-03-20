package src.model.instances;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import src.model.interfaces.GameTile;
import src.model.interfaces.MovementListener;
import src.model.interfaces.RadiusListener;
import src.model.interfaces.StatsListener;
import src.model.interfaces.vInstance;
import src.model.interfaces.LocatableVisitor;

public abstract class Instance implements vInstance, CommandSender, Locatable
{
	private GameTile location;
	public Instance( GameTile g )
	{
		location = g;
	}
	
	final public GameTile location()
	{
		return location;
	}
	
	private Map< String, Integer > stats = new HashMap< String, Integer >();
	public Map< String, Integer > getStats()
	{
		return Collections.unmodifiableMap( stats );
	}
	
	final public int influenceRadius()
	{
		return stats.get( "influenceRadius" );
	}
	
	final public int getStat( String s )
	{
		return stats.get( s );
	}
	
	final public void setStat( String s, int val )
	{
		stats.put( s, val );
		for ( StatsListener sl : statsListeners )
			sl.statsChanged( this );
		
		if ( s.equals( "influenceRadius" ) )
			for ( RadiusListener rl : radiusListeners )
				rl.radiusChanged( this );
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
	
	private List< CommandListener > commandListeners = new LinkedList< CommandListener >();
	final public void addCommandListener( CommandListener cl )
	{
		commandListeners.add( cl );
	}
	
	final public void executeCommand( Command c )
	{
		for ( CommandListener cl : commandListeners )
			cl.commandOccurred( c );
	}
	
	private List< MovementListener > moveListeners = new LinkedList< MovementListener >();
	final public void addMovementListener( MovementListener cl )
	{
		moveListeners.add( cl );
	}
	
	final public void setLocation( GameTile g )
	{
		GameTile prev = location;
		location = g;
		for ( MovementListener ml : moveListeners )
			ml.instanceMoved( this, prev );
	}
	
	final public void accept( LocatableVisitor lv )
	{
		lv.visitInstance( this );
	}
	
	private List< RadiusListener > radiusListeners = new LinkedList< RadiusListener >();
	final public void addRadiusListener( RadiusListener cl )
	{
		radiusListeners.add( cl );
	}
}
