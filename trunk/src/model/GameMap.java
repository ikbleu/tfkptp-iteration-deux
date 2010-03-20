package src.model;

import src.model.enums.Direction;
import src.model.enums.TerrainType;
import src.model.interfaces.GameTile;

public class GameMap
{
	private HexTile origin, startingLocation1, startingLocation2;
	final int MAP_RADIUS = 9;
	
	public GameMap()
	{
		origin = new HexTile();
		populate(origin);
		
		for (startingLocation1 = origin;
			 startingLocation1.getNeighborHex(Direction.N) != null;
			 startingLocation1 = startingLocation1.getNeighborHex(Direction.N));
		
		for (startingLocation2 = origin;
		 	 startingLocation2.getNeighborHex(Direction.S) != null;
		 	 startingLocation2 = startingLocation2.getNeighborHex(Direction.S));
		
		startingLocation1.setTerrainType(TerrainType.GRASSLAND);
		startingLocation2.setTerrainType(TerrainType.GRASSLAND);
	}
	
	public GameTile getOrigin()
	{
		return origin;
	}
	
	public GameTile getStartingLocation1()
	{
		return startingLocation1;
	}
	
	public GameTile getStartingLocation2()
	{
		return startingLocation2;
	}
	
	public int getMapRadius()
	{
		return MAP_RADIUS;
	}
	
	public VisibilityMap toVisibility()
	{
		return new VisibilityMap();
	}
	
	public void unmarkAll()
	{
		origin.unmark();
	}
	
	private void populate(HexTile tile)
	{
		Direction d = Direction.N;
		System.out.println("Making tile " + tile.getX() + " " + tile.getY() + " " + tile.getZ() + "...");
		do
		{
			tile.setNeighbor(d, new HexTile(tile,d));
			d = d.clockwise();
		} while (d != Direction.N);
		
		tile.linkNeighbors();
		tile.randomize();
		do
		{
			populateHelper(tile.getNeighborHex(d), d.opposite(), MAP_RADIUS);
			d = d.clockwise();
		} while (d != Direction.N);
		
	}
	
	private void populateHelper(HexTile tile, Direction parentDir, int radius)
	{
		tile.randomize();
		
		if (radius <= 1) return;
		
		//System.out.println("Making tile " + tile.getX() + " " + tile.getY() + " " + tile.getZ() + "...");
		Direction d = Direction.N;
		do
		{
			if (d != parentDir)
				tile.setNeighbor(d, new HexTile(tile,d));
			d = d.clockwise();
		} while (d != Direction.N);
		
		tile.linkNeighbors();
		
		do
		{
			if (d != parentDir)
				populateHelper(tile.getNeighborHex(d), d.opposite(), radius - 1);
			d = d.clockwise();
		} while (d != Direction.N);
		
	}
}
