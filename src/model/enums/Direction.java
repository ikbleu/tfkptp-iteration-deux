package src.model.enums;
/**
 * 
 * @author AdamSzpakowski
 *
 */
public enum Direction
{
	N(-1, 1, "N"),
	NE(0, 1, "NE"),
	SE(1, 0, "SE"),
	S(1, -1, "S"),
	SW(0,-1, "SW"),
	NW(-1, 0, "NW");
	
	public final int x, y;
	public final String direction; 
	
	private Direction(int x, int y, String s)
	{
		this.x = x;
		this.y = y;
		direction = s;
	}
	
	public Direction opposite()
	{
		return Direction.values()[(this.ordinal()+3)%6];
	}
	
	public Direction clockwise()
	{
		return Direction.values()[(this.ordinal()+1)%6];
	}
	
	public Direction counterclockwise()
	{
		return Direction.values()[(this.ordinal()+5)%6];
	}
	
	public Direction clockwiseOpposite()
	{
		return clockwise().opposite();
	}
	
	public Direction counterclockwiseOpposite()
	{
		return counterclockwise().opposite();
	}
}
