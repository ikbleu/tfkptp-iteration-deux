package src.model;

import src.model.interfaces.GameTile;

class GameMap
{
	HexTile origin, startingLocation1, startingLocation2;
	int mapRadius;
	
	public GameMap()
	{
		
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
		return mapRadius;
	}
	
	public VisibilityMap toVisibility()
	{
		return new VisibilityMap();
	}
	
	public void unmarkAll()
	{
		
	}
}
