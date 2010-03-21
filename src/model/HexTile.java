package src.model;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import src.model.enums.Direction;
import src.model.interfaces.GameTile;
import src.model.interfaces.Resource;
import src.model.interfaces.Token;

class HexTile implements GameTile
{
	private EnumMap<Direction,HexTile> neighbors;
	private String terrain;
	private MysteryPoint coordinate;
	private boolean mark;
	Set<Resource> resources;
	
	public HexTile(String terrain)
	{
		neighbors = new EnumMap<Direction,HexTile>(Direction.class);
		this.terrain = terrain;
		coordinate = new MysteryPoint();
		mark = false;
	}
	
	public HexTile(String terrain, HexTile par, Direction dir)
	{
		neighbors = new EnumMap<Direction,HexTile>(Direction.class);
		this.terrain = terrain;
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
				if (hasNeighbor(d))
					neighbors.get(d).unmark();
				d = d.clockwise();
			} while (d != Direction.N);
		}
	}
	
	public String toString()
	{
		return "(" + getX() + ", " + getY() + ", " + getZ() + "): " + terrain;
	}

	public String getTerrainType()
	{
		return terrain;
	}
	
	void setTerrainType(String t)
	{
		terrain = t;
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
	
	public void setResources(Set<Resource> s)
	{
		resources = s;
	}
	
	public Resource getResource(String s)
	{
		Iterator<Resource> i = resources.iterator();
		while (i.hasNext())
		{
			Resource r = i.next();
			if (r.getResourceType().equals(s))
				return r;
			
		}
		return null;
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
			z = x + y;
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
	public boolean hasNeighbor(Direction d) {
		
		return neighbors.get(d) != null;
	}

	public List<GameTile> getTilesAround(int radius)
	{
		List<GameTile> l = new ArrayList<GameTile>();
		getTilesAroundHelper(l, this, radius);
		this.unmark();		
		return l;
	}
	
	private void getTilesAroundHelper(List<GameTile> list, GameTile tile, int radius)
	{
		if (!tile.isMarked())
			list.add(tile);
		tile.mark();
		
		
		if (radius > 0)
		{
			
			Direction d = Direction.N;
			do
			{
				if (tile.hasNeighbor(d))
					getTilesAroundHelper(list, tile.getNeighbor(d), radius - 1);
				d = d.clockwise();
			} while (d != Direction.N);
		}
	}
	
	public int getDistanceFrom(GameTile t)
	{
		return (Math.abs(this.getX() - t.getX())+ Math.abs(this.getY() - t.getY())+ Math.abs(this.getZ() - t.getZ()))/2;
	}
	
	public List<Direction> getDirectionsTo(GameTile t)
	{
		List<Direction> l = new ArrayList<Direction>();
		
		System.out.println("Getting directions from " + this + " to " + t);
		
		getDirectionsHelper(l,this, t);
		
		return l;
	}
	
	private void getDirectionsHelper(List<Direction> list, GameTile t1, GameTile t2)
	{
		if (t1 == t2)
			return;
		
		System.out.println(t1 + " to " + t2);
		
		Direction d;
		
		if (t1.getZ() != t2.getZ())
		{
			if (t2.getZ() < t1.getZ()) // 
			{
				if (t2.getY() < t1.getY()) // move SE
				{
					d = Direction.SW;
				}
				else
				{
					d = Direction.NW;
				}
			}
			else
			{
				if (t2.getY() <= t1.getY())
					d = Direction.SE;
				else
					d = Direction.NE;
			}
		}
		else
		{
			if (t1.getY() > t2.getY())
				d = Direction.S;
			else
				d = Direction.N;
		}
		
		list.add(d);
		getDirectionsHelper(list, t1.getNeighbor(d), t2);
	}
	
	public List<GameTile> getTilesInDirection(Direction dir, int distance)
	{
		List<GameTile> l = new ArrayList<GameTile>();
		
		if (distance > 0 && this.hasNeighbor(dir))
			getTilesInDirectionHelper(l, this.getNeighbor(dir), dir, distance - 1);
		
		return l;
	}
	
	private void getTilesInDirectionHelper(List<GameTile> list, GameTile t, Direction dir, int distance)
	{
		list.add(t);
		
		if (distance >0 && t.hasNeighbor(dir))
			getTilesInDirectionHelper(list, t.getNeighbor(dir), dir, distance - 1 );
	}
}
