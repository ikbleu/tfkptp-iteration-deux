package src.model;

import java.util.EnumMap;
import java.util.List;

import src.model.enums.Direction;
import src.model.enums.TerrainType;
import src.model.interfaces.Resource;

class HexTile
{
	private EnumMap<Direction,HexTile> neighbors;
	private List<Resource> resources;
	private TerrainType terrain;
	private Item item;
	private MysteryPoint coordinate;
	
	public HexTile()
	{
		
	}
	
	void setNeighbor(Direction dir, HexTile ht)
	{
		
	}
	
	HexTile getNeighbor(Direction dir)
	{
		
	}
	
	void randomize()
	{
		
	}
	
	void mark()
	{
		
	}
	
	boolean isMarked()
	{
		
	}
	
	void unmark()
	{
		
	}
	
	void populate(int radius)
	{
		
	}

	boolean isWalkableOn(UnitTerrainWalkability utw, UnitType type)
	{
		
	}
	
	boolean isSafeToWalkOn(UnitTerrainWalkability utw, UnitType type)
	{
		
	}
	
	void acceptItemVisitor(ItemVisitor visitor)
	{
		
	}
	
	TerrainType getTerrainType()
	{
		
	}
	
	public int getX()
	{
		return coordinate.getX();
	}
	
	public int getY()
	{
		return coordinate.getY();
	}
	
	public int getZ()
	{
		return coordinate.getZ();
	}
	
	private class MysteryPoint
	{
		private int x, y, z;
		
		private MysteryPoint()
		{
			x = y = z = 0;
		}
		
		private MysteryPoint(MysteryPoint par, Direction dir)
		{
			x = par.x + dir.x;
			y = par.y + dir.y;
			z = par.z + dir.z;
		}
		
		public int getX()
		{
			return x;
		}
		
		public int getY()
		{
			return y;
		}
		
		public int getZ()
		{
			return z;
		}
	}
}
