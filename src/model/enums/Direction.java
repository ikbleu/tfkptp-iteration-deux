package src.model.enums;
/**
 * 
 * @author AdamSzpakowski
 *
 */
public enum Direction
{
	N(-1, 1),
	NE(0, 1),
	SE(1, 0),
	S(1, -1),
	SW(0,-1),
	NW(-1, 0);
	
	public final int x, y;
	
	private Direction(int x, int y)
	{
		this.x = x;
		this.y = y;
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
