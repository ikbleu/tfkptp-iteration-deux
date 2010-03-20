package src.model.enums;

public enum TerrainType
{
	GRASSLAND("Grassland"),
	WATER("Water"),
	SPARSE_FOREST("Sparse Forest"),
	OUTER_SPACE("Outer Space"),
	VOID("void");
	
	public final String type;
	
	private TerrainType(String s)
	{
		type = s;
	}
}
