package src.model.interfaces;

import src.model.enums.Direction;
import src.model.enums.TerrainType;
import src.model.TokenTerrainWalkability;

public interface GameTile {
	public GameTile getNeighbor(Direction d);
	public boolean isWalkable(TokenTerrainWalkability utw, Token i);
	public boolean isSafeToWalkOn(TokenTerrainWalkability utw, Token i);
	public int getX();
	public int getY();
	public int getZ();
	public void mark();
	public boolean isMarked();
	public void unmark();
	public TerrainType getTerrainType();
}
