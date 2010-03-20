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
		return null;
	}
	
	private static void getTilesAroundHelper(List<GameTile> list, GameTile tile, int radius)
	{
		list.add(tile);
		tile.mark();
		if (radius > 0)
		{
			Direction d = Direction.N;
			do
			{
				getTilesAroundHelper(list, tile.getNeighbor(d), radius -1);
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
		
		getDirectionsHelper(l,t1, t2);
		
		return null;
	}
	
	private static void getDirectionsHelper(List<Direction> list, GameTile t1, GameTile t2)
	{
		if (t1 == t2)
			return;
		
		Direction d;
		
		if (t1.getZ() != t2.getZ())
		{
			if (t1.getY() >= t2.getY())
			{
				if (t1.getZ() < t2.getZ()) // move SE
				{
					d = Direction.SE;
				}
				else
				{
					d = Direction.NW;
				}
			}
		}
		
		list.add(Direction.SE);
		getDirectionsHelper(list, t1.getNeighbor(Direction.SE), t2);
	}
	
	public static List<GameTile> getTilesInDirection(GameTile t, Direction dir, int distance)
	{
		return null;
	}
	
	public static GameTile getNeighborAt(GameTile t, Direction d)
	{
		return t.getNeighbor(d);
	}
	
	public static boolean hasNeighborAt(GameTile t, Direction d)
	{
		return t.getNeighbor(d) != null;
	}
}
