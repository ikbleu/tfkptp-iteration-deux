package src.model.interfaces;

import java.util.List;
import java.util.Map;

import src.model.enums.Direction;
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
	/**
	 * Unmarks the tile and all tiles around it that are marked
	 */
	public void unmark();
	public String getTerrainType();
	public boolean hasNeighbor(Direction d);
	public Resource getResource(String s);
	public Map<String, Integer> getResources();
	
	// spacial methods
	public List<GameTile> getTilesAround(int radius);
	public int getDistanceFrom(GameTile t);
	public List<Direction> getDirectionsTo(GameTile t);
	public List<GameTile> getTilesInDirection(Direction dir, int distance);
}

