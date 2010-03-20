package src.model.interfaces;

import src.model.enums.Direction;

public interface GameTile {
	public GameTile getNeighbor(Direction d);

}
