package src.model;

public class StructureTileAssociation
{
	Structure structure;
	HexTile location;
	
	public StructureTileAssociation(Structure s, HexTile h)
	{
		structure = s;
		location = h;
	}
	
	public Structure getStructure()
	{
		return structure;
	}
	
	public HexTile getHexTile()
	{
		return location;
	}
}
