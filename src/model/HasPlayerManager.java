package src.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import src.model.exceptions.YoureDoingItWrongException;
import src.model.interfaces.GameTile;
import src.model.interfaces.HasPlayer;

public class HasPlayerManager
{
	Map<GameTile,Set<HasPlayer> > hasPlayerMap;
	
	private static class HasPlayerManagerHolder
	{
		private static HasPlayerManager INSTANCE = new HasPlayerManager();
	}
	
	public static HasPlayerManager getInstance()
	{
		return HasPlayerManagerHolder.INSTANCE;
	}
	
	private HasPlayerManager()
	{
		hasPlayerMap = new HashMap<GameTile, Set<HasPlayer> >();
	}
	
	public boolean add(GameTile t, HasPlayer hp)
	{
		if(!hasPlayerMap.containsKey(t))
		{
			Set<HasPlayer> s = new HashSet<HasPlayer>();
			s.add(hp);
			hasPlayerMap.put(t, s);
			return true;
		}
		else
		{
			Set<HasPlayer> s = hasPlayerMap.get(t);
			if (hp.hasSamePlayer(s.iterator().next()))
			{
				s.add(hp);
				hasPlayerMap.put(t, s);
				return true;
			}
		}
		
		return false;
	}
	
	public void remove(GameTile t, HasPlayer hp)
	{
		
	}
	
	public Player getPlayerOf(GameTile t)
	{
		if (!hasPlayerMap.containsKey(t))
			return null;
		
		Set<HasPlayer> s = hasPlayerMap.get(t);
		return s.iterator().next().getPlayer();
		
	}
	
	public boolean move(GameTile to, GameTile from, HasPlayer hp) throws YoureDoingItWrongException
	{
		if(!hasPlayerMap.containsKey(to))
		{
			Set<HasPlayer> s = new HashSet<HasPlayer>();
			s.add(hp);
			
			Set<HasPlayer> s2 = hasPlayerMap.get(from);
			
			if (s2 == null || !s2.contains(hp))
				throw new YoureDoingItWrongException("Incorrect parameter usage: " +
													 "from must be HasPlayer's previous location");
			
			s2.remove(hp);
			
			if (s2.isEmpty())
				hasPlayerMap.remove(from);
			else
				hasPlayerMap.put(from, s2);
			
			hasPlayerMap.put(to, s);
			
			return true;
		}
		else
		{
			Set<HasPlayer> s = hasPlayerMap.get(to);
			if (hp.hasSamePlayer(s.iterator().next()))
			{
				s.add(hp);
				
				Set<HasPlayer> s2 = hasPlayerMap.get(from);
				
				if (s2 == null || !s2.contains(hp))
					throw new YoureDoingItWrongException("Incorrect parameter usage: " +
														 "from must be HasPlayer's previous location");
				
				s2.remove(hp);
				
				if (s2.isEmpty())
					hasPlayerMap.remove(from);
				else
					hasPlayerMap.put(from, s2);
				
				hasPlayerMap.put(to, s);
				
				return true;
			}
		}
		
		return false;
	}

	public Set<HasPlayer> getThingsIn(Set<GameTile> visibleTiles) {
		// TODO Auto-generated method stub
		Set<HasPlayer> s = new HashSet<HasPlayer>();
		
		Iterator<GameTile> i = visibleTiles.iterator(); 
		while (i.hasNext())
		{
			Set<HasPlayer> temp = hasPlayerMap.get(i.next());
			if (temp != null)
				s.addAll(temp);
		}
		
		return s;
	}
}
