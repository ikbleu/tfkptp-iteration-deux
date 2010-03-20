package src.model.interfaces;

import src.model.instances.Instance;

public interface MovementListener {
	public void instanceMoved( Instance i, GameTile previous );
}
