package src.model.instances;

import src.model.interfaces.GameTile;
import src.model.interfaces.LocatableVisitor;
import src.model.interfaces.RadiusListener;

public interface Locatable {
	GameTile location();
	int influenceRadius();
	void addRadiusListener( RadiusListener rl );
	void accept( LocatableVisitor lv );
	void instanceEntered( Instance i );
	void instanceExited( Instance i );
}
