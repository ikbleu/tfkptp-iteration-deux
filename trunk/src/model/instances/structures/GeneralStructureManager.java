package src.model.instances.structures;

import java.util.LinkedList;
import java.util.List;

import src.model.HasPlayerManager;
import src.model.Player;
import src.model.instances.Instance;
import src.model.instances.InstanceExistenceListener;
import src.model.interfaces.GameTile;

public class GeneralStructureManager implements InstanceExistenceListener {
	private int numStructures = 0;
	private List< GameTile > invalid = new LinkedList< GameTile >();
	
	public GeneralStructureManager( Player p )
	{
		player = p;
	}
	private Player player;
	
	public boolean canMakeStructure( GameTile g )
	{
		// TODO: make sure tile isn't owned by enemy
		boolean isInvalid = invalid.contains( g );
		boolean enemyThere = HasPlayerManager.getInstance().getPlayerOf( g ) != player;
		return ( ! ( isInvalid || enemyThere ) );
	}
	
	public void delInstance(Instance i) {
		--numStructures;
		invalid.add( i.location() );
		System.out.println( "we now have " + numStructures + " structures" );
	}
	
	public void newInstance(Instance i) {
		++numStructures;
		invalid.remove( i.location() );
		System.out.println( "we now have " + numStructures + " structures" );
	}
}
