package src.model;

import java.util.Set;

import src.model.enums.Direction;
import src.model.interfaces.GameTile;
import src.model.interfaces.StringRandomizer;
import src.view.MapBuilder;

public class GameMap
{
	private HexTile origin, startingLocation1, startingLocation2;
	final int MAP_RADIUS = 9;
	private StringRandomizer rand;
	ResourceMaker resourceGrabber;
	Set<String> resourceTypes;
	
	public GameMap(Model m)
	{
		rand = new TerrainRandomizer();
		resourceGrabber = new ResourceMaker(m.getClock());
		resourceTypes = resourceGrabber.possibleResourceValues();
		
		origin = new HexTile(rand.random());
		populate(origin);
		
		for (startingLocation1 = origin;
			 startingLocation1.getNeighborHex(Direction.N) != null;
			 startingLocation1 = startingLocation1.getNeighborHex(Direction.N));
		
		for (startingLocation2 = origin;
		 	 startingLocation2.getNeighborHex(Direction.S) != null;
		 	 startingLocation2 = startingLocation2.getNeighborHex(Direction.S));
		
		startingLocation1.setTerrainType(rand.defaultValue());
		startingLocation1.setResources(resourceGrabber.getResources(false));
		
		startingLocation2.setTerrainType(rand.defaultValue());
		startingLocation2.setResources(resourceGrabber.getResources(false));
		
		
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
	
	public int getDiameter()
	{
		return 2 * MAP_RADIUS + 1;
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
			tile.setNeighbor(d, new HexTile(rand.random(), tile,d));
			d = d.clockwise();
		} while (d != Direction.N);
		
		tile.linkNeighbors();
		do
		{
			populateHelper(tile.getNeighborHex(d), d.opposite(), MAP_RADIUS);
			d = d.clockwise();
		} while (d != Direction.N);
		
	}
	
	private void populateHelper(HexTile tile, Direction parentDir, int radius)
	{
		tile.setResources(resourceGrabber.getResources(true));
		if (radius <= 1) return;
		
		//System.out.println("Making tile " + tile.getX() + " " + tile.getY() + " " + tile.getZ() + "...");
		Direction d = Direction.N;
		do
		{
			if (d != parentDir)
				tile.setNeighbor(d, new HexTile(rand.random(), tile,d));
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
	
	public GameTile acceptBuilder(MapBuilder mb, int horiz, int vert)
	{
		if (isValid2DCoordinate(horiz, vert))
		{
			GameTile gt = origin.getTileAt2DCoordinate(horiz,vert);
			mb.setTerrain(gt.getTerrainType());
			return gt;
		}
		return null;
	}
	
	private boolean isValid2DCoordinate(int horiz, int vert)
	{
		if (origin.calculateDistance(horiz - MAP_RADIUS, vert - MAP_RADIUS) > MAP_RADIUS)
			return false;
		
		return true;
	}
	
}
