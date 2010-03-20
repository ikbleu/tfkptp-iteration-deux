package src.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import src.model.enums.TerrainType;

class UnitTerrainWalkability
{
	private Map <UnitType, List<TerrainSafety> > unitTerrainMap;
	
	public UnitTerrainWalkability ()
	{
		unitTerrainMap = new HashMap<UnitType, List<TerrainSafety> > ();
	}
	
	void add (UnitType unit, TerrainType terrain, boolean safe)
	{
		
	}
	
	void remove (UnitType unit, TerrainType terrain)
	{
		
	}
	
	boolean contains (UnitType unit, TerrainType terrain)
	{
		
	}
	
	boolean isSafe (UnitType unit, TerrainType terrain)
	{
		
	}
	
	private class TerrainSafety
	{
		TerrainType terrain;
		boolean safe;
		
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
	}
}
