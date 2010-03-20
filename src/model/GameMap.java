package src.model;

class GameMap
{
	HexTile origin, startingLocation1, startingLocation2;
	int mapRadius;
	
	public GameMap()
	{
		
	}
	
	public HexTile getOrigin()
	{
		return origin;
	}
	
	public HexTile getStartingLocation1()
	{
		return startingLocation1;
	}
	
	public HexTile getStartingLocation2()
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
