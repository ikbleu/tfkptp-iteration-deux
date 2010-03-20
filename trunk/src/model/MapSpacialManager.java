package src.model;

import java.util.ArrayList;
import java.util.List;

import src.model.enums.Direction;
import src.model.interfaces.GameTile;

public class MapSpacialManager
{
	public static List<GameTile> getTilesAround(GameTile tile, int radius)
	{
		List<GameTile> l = new ArrayList<GameTile>();
		getTilesAroundHelper(l, tile, radius);
		tile.unmark();		
		return l;
	}
	
	private static void getTilesAroundHelper(List<GameTile> list, GameTile tile, int radius)
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
	
	public static int getDistanceBetween(GameTile t1, GameTile t2)
	{
		return (Math.abs(t1.getX() - t2.getX())+ Math.abs(t1.getY() - t2.getY())+ Math.abs(t1.getZ() - t2.getZ()))/2;
	}
	
	public static List<Direction> getDirections(GameTile t1, GameTile t2)
	{
		List<Direction> l = new ArrayList<Direction>();
		
		System.out.println("Getting directions from " + t1 + " to " + t2);
		
		getDirectionsHelper(l,t1, t2);
		
		return l;
	}
	
	private static void getDirectionsHelper(List<Direction> list, GameTile t1, GameTile t2)
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
	
	public static List<GameTile> getTilesInDirection(GameTile t, Direction dir, int distance)
	{
		List<GameTile> l = new ArrayList<GameTile>();
		
		if (distance > 0 && t.hasNeighbor(dir))
			getTilesInDirectionHelper(l, t.getNeighbor(dir), dir, distance - 1);
		
		return l;
	}
	
	private static void getTilesInDirectionHelper(List<GameTile> list, GameTile t, Direction dir, int distance)
	{
		list.add(t);
		
		if (distance >0 && t.hasNeighbor(dir))
			getTilesInDirectionHelper(list, t.getNeighbor(dir), dir, distance - 1 );
	}
}
