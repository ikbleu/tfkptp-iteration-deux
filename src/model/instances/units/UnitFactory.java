package src.model.instances.units;

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
	
	abstract public Unit makeInstance( GameTile loc );
}
