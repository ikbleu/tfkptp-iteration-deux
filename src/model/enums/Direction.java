package src.model.enums;
/**
 * 
 * @author AdamSzpakowski
 *
 */
public enum Direction
{
	N(-1, 1, 0),
	NE(0, 1, 1),
	SE(1, 0, 1),
	S(1, -1, 0),
	SW(0,-1,-1),
	NW(-1, 0, -1);
	
	public final int x, y, z;
	
	private Direction(int x, int y, int z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
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
}
