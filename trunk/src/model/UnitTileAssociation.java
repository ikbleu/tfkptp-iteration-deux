package src.model;

public class UnitTileAssociation
{
	Unit unit;
	HexTile location;
	
	public UnitTileAssociation(Unit u, HexTile h)
	{
		unit = u;
		location = h;
	}
	
	public Unit getUnit()
	{
		return unit;
	}
	
	public HexTile getHexTile()
	{
		return location;
	}
}
