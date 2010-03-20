package src.model;

public class RallyPointTileAssociation
{
	RallyPoint rallypoint;
	HexTile location;
	
	public RallyPointTileAssociation(RallyPoint rp, HexTile h)
	{
		rallypoint = rp;
		location = h;
	}
	
	public RallyPoint getRallyPoint()
	{
		return rallypoint;
	}
	
	public HexTile getHexTile()
	{
		return location;
	}
}

