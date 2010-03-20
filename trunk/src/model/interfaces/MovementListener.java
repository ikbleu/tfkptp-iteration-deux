package src.model.interfaces;

import src.model.instances.Locatable;

public interface MovementListener {
	public void locationChanged( Locatable l, GameTile previous );
}
