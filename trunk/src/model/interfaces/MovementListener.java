package src.model.interfaces;

import src.model.instances.Locatable;

public interface MovementListener {
	public void instanceMoved( Locatable l, GameTile previous );
}
