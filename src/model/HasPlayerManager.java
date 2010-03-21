package src.model;

import java.util.Map;
import java.util.Set;

import src.model.interfaces.GameTile;
import src.model.interfaces.HasPlayer;

public class HasPlayerManager
{
	Map<GameTile,Set<HasPlayer> > hasPlayerMap;
	
	public boolean add(GameTile t, HasPlayer hp)
	{
		return false;
	}
	
	public void remove(GameTile t, HasPlayer hp)
	{
		
	}
	
	public boolean move(GameTile to, GameTile from, HasPlayer hp)
	{
		return false;
	}
}
