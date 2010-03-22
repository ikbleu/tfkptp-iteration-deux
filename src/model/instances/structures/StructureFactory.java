package src.model.instances.structures;

import java.util.HashMap;
import java.util.Map;

import src.model.Player;
import src.model.instances.InstanceExistenceListener;
import src.model.instances.InstanceFactory;
import src.model.instances.Structure;
import src.model.instances.Unit;
import src.model.interfaces.GameTile;

abstract class StructureFactory implements InstanceFactory, InstanceExistenceListener {
	public StructureFactory( Player p )
	{
		player = p;
	}
	private Player player;
	public Player player()
	{
		return player;
	}
	
	final public Structure makeInstance( GameTile g )
	{
		Structure s = doMakeInstance( g );
		for ( Map.Entry< String, Integer > stat : stats.entrySet() )
		{
			s.modifyStat(stat.getKey(), stat.getValue() );
			System.out.printf( "this unit's default val for %s is %d\n", stat.getKey(), stat.getValue() );
		}
		
		return s;
	}
	private Map< String, Integer > stats = new HashMap< String, Integer >();
	
	public void modDefaultStat( String statToken, int delta )
	{
		Integer old = stats.get( statToken );
		old = old == null ? 0 : old;
		stats.put( statToken, old + delta );
	}
	
	abstract public Structure doMakeInstance( GameTile loc );
}
