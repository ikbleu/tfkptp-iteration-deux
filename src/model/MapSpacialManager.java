package src.model;

import java.util.List;

import src.model.enums.Direction;
import src.model.interfaces.GameTile;

public class MapSpacialManager
{
	public static List<GameTile> getTilesAround(GameTile tile, int radius)
	{
		return null;
	}
	
	public static int getDistanceBetween(GameTile t1, GameTile t2)
	{
		return (Math.abs(t1.getX() - t2.getX())+ Math.abs(t1.getY() - t2.getY())+ Math.abs(t1.getZ() - t2.getZ()))/2;
	}
	
	public static List<Direction> getDirections(GameTile t1, GameTile t2)
	{
		return null;
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
