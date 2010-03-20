package src.model;

import java.util.EnumMap;

import src.model.enums.Direction;
import src.model.enums.TerrainType;
import src.model.interfaces.GameTile;
import src.model.interfaces.Token;

class HexTile implements GameTile
{
	private EnumMap<Direction,HexTile> neighbors;
	private TerrainType terrain;
	private MysteryPoint coordinate;
	private boolean mark;
	
	public HexTile()
	{
		neighbors = new EnumMap<Direction,HexTile>(Direction.class);
		terrain = TerrainType.GRASSLAND;
		coordinate = new MysteryPoint();
		mark = false;
	}
	
	public HexTile(HexTile par, Direction dir)
	{
		neighbors = new EnumMap<Direction,HexTile>(Direction.class);
		terrain = TerrainType.GRASSLAND;
		setNeighbor(dir.opposite(),par);
		coordinate = new MysteryPoint(par.coordinate,dir);
		mark = false;
		
	}
	
	void setNeighbor(Direction dir, HexTile ht)
	{
		if (neighbors.get(dir) == null)
			neighbors.put(dir, ht);
	}
	
	public GameTile getNeighbor(Direction dir)
	{
		return neighbors.get(dir);
	}
	
	public HexTile getNeighborHex(Direction dir)
	{
		return neighbors.get(dir);
	}
	
	void randomize()
	{
		int i = (int)(Math.random()*100);
		
		if (i > 49)
		{
			if (i <= 79)
				terrain = TerrainType.SPARSE_FOREST;
			else if (i <= 94)
				terrain = TerrainType.WATER;
			else
				terrain = TerrainType.OUTER_SPACE;
		}
	}
	
	public void mark()
	{
		mark = true;
	}
	
	public boolean isMarked()
	{
		return mark;
	}
	
	public void unmark()
	{
		if (mark)
		{
			mark = false;
			Direction d = Direction.N;
			do
			{
				neighbors.get(d).unmark();
				d = d.clockwise();
			} while (d != Direction.N);
		}
	}

	public TerrainType getTerrainType()
	{
		return terrain;
	}
	
	void setTerrainType(TerrainType t)
	{
		terrain = t;
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
	
	public void linkNeighbors()
	{
		Direction d = Direction.N;
		
		do
		{
			if (neighbors.get(d) != null)
				neighbors.get(d).setNeighbor(d.counterclockwiseOpposite(), neighbors.get(d.clockwise()));
			if (neighbors.get(d.clockwise()) != null)
				neighbors.get(d.clockwise()).setNeighbor(d.counterclockwise(), neighbors.get(d));

			d = d.clockwise();
		} while (d != Direction.N);
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

	@Override
	public boolean isSafeToWalkOn(TokenTerrainWalkability utw, Token i) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isWalkable(TokenTerrainWalkability utw, Token i) {
		// TODO Auto-generated method stub
		return false;
	}
}
