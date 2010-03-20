package src.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import src.model.interfaces.Token;

public class TokenTerrainWalkability
{
	private Map <Token, List<TerrainSafety> > map;
	
	public TokenTerrainWalkability ()
	{
		map = new HashMap<Token, List<TerrainSafety> > ();
	}
	
	void add (Token tkn, String terrain, boolean safe)
	{
		TerrainSafety ts = new TerrainSafety(terrain, safe);
		if (!contains(tkn, terrain))
		{
			if (!map.containsKey(tkn))
			{
				List<TerrainSafety> l = new ArrayList<TerrainSafety>();
				l.add(ts);
				map.put(tkn, l);
			}
			else
			{
				List<TerrainSafety> l = map.get(tkn);
				l.add(ts);
				map.put(tkn, l);
			}
		}
	}
	
	void remove (Token tkn, String terrain)
	{
		if (contains(tkn, terrain))
		{
			List<TerrainSafety> l = map.get(tkn);
			Iterator<TerrainSafety> i = l.iterator();
			while (i.hasNext())
			{
				if (i.next().getTerrainType() == terrain)
				{
					i.remove();
					break;
				}
			}
		}
	}
	
	boolean contains (Token tkn, String terrain)
	{
		if (map.containsKey(tkn))
		{
			List<TerrainSafety> l = map.get(tkn);

			for (int i = 0; i < l.size(); i++)
				if (l.get(i).getTerrainType() == terrain)
					return true;
		}
		return false;
	}
	
	boolean isSafe (Token tkn, String terrain)
	{
		if (map.containsKey(tkn))
		{
			List<TerrainSafety> l = map.get(tkn);
			
			for (int i = 0; i < l.size(); i++)
				if (l.get(i).getTerrainType() == terrain)
					return l.get(i).getSafety();
		}
		return false;
	}
	
	private class TerrainSafety
	{
		private String terrain;
		private boolean safe;
		
		public TerrainSafety(String terrain, boolean safe)
		{
			this.terrain = terrain;
			this.safe = safe;
		}
		
		public String getTerrainType()
		{
			return terrain;
		}
		
		public boolean getSafety()
		{
			return safe;
		}
		
	}
}
