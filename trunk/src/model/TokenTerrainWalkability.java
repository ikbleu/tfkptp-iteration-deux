package src.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import src.model.enums.TerrainType;
import src.model.interfaces.Token;

public class TokenTerrainWalkability
{
	private Map <Token, List<TerrainSafety> > map;
	
	public TokenTerrainWalkability ()
	{
		map = new HashMap<Token, List<TerrainSafety> > ();
	}
	
	void add (Token tkn, TerrainType terrain, boolean safe)
	{
		
	}
	
	void remove (Token tkn, TerrainType terrain)
	{
		
	}
	
	boolean contains (Token tkn, TerrainType terrain)
	{
		if (map.containsKey(tkn))
		{
			List<TerrainSafety> l = map.get(tkn);
			if (l.contains(new TerrainSafety(terrain,true)))
				return true;
		}
		return false;
	}
	
	boolean isSafe (Token tkn, TerrainType terrain)
	{
		if (map.containsKey(tkn))
		{
			List<TerrainSafety> l = map.get(tkn);
			
			TerrainSafety ts = new TerrainSafety(terrain,true);
			
			if (l.contains(ts))
			{
				return l.get(l.indexOf(ts)).getSafety();
			}
		}
		return false;
	}
	
	private class TerrainSafety
	{
		private TerrainType terrain;
		private boolean safe;
		
		public TerrainSafety(TerrainType terrain, boolean safe)
		{
			this.terrain = terrain;
			this.safe = safe;
		}
		
		public TerrainType getTerrainType()
		{
			return terrain;
		}
		
		public boolean getSafety()
		{
			return safe;
		}
		
		public boolean equals(TerrainSafety t)
		{
			return terrain == t.terrain;
		}
		
	}
}
