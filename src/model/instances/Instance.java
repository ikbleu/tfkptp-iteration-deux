package src.model.instances;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import src.model.interfaces.GameTile;
import src.model.interfaces.StatsListener;
import src.model.interfaces.vInstance;
import src.model.interfaces.LocatableVisitor;

public abstract class Instance extends Locatable implements vInstance, CommandSender
{
	public Instance( GameTile g )
	{
		super( g );
	}
	
	private Map< String, Integer > stats = new HashMap< String, Integer >();
	public Map< String, Integer > getStats()
	{
		return Collections.unmodifiableMap( stats );
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
			setInfluenceRadius( val );
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
	
	final public void updateLocation( GameTile prev )
	{
		// don't need to do anything
	}
	
	final public void accept( LocatableVisitor lv )
	{
		lv.visitInstance( this );
	}
	
	abstract public String token();
}
