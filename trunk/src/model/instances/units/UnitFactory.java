package src.model.instances.units;

import java.util.HashMap;
import java.util.Map;

import src.model.Player;
import src.model.instances.InstanceFactory;
import src.model.instances.Unit;
import src.model.interfaces.GameTile;

abstract class UnitFactory implements InstanceFactory {
	public UnitFactory( Player p )
	{
		player = p;
	}
	private Player player;
	public Player player()
	{
		return player;
	}
	
	final public Unit makeInstance( GameTile g )
	{
		Unit u = doMakeInstance( g );
		for ( Map.Entry< String, Integer > stat : stats.entrySet() )
		{
			u.modifyStat(stat.getKey(), stat.getValue() );
			System.out.printf( "this unit's default val for %s is %d\n", stat.getKey(), stat.getValue() );
		}
		
		return u;
	}
	private Map< String, Integer > stats = new HashMap< String, Integer >();
	
	public void modDefaultStat( String statToken, int delta )
	{
		Integer old = stats.get( statToken );
		old = old == null ? 0 : old;
		stats.put( statToken, old + delta );
	}
	
	abstract public Unit doMakeInstance( GameTile loc );
}
