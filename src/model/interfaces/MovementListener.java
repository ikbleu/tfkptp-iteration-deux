package src.model.interfaces;

import src.model.instances.Instance;

public interface MovementListener {
	public void locationChanged( Instance i, GameTile previous );
}
