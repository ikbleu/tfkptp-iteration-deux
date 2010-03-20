package src.model.instances;

import src.model.interfaces.GameTile;
import src.model.interfaces.LocatableVisitor;
import src.model.interfaces.RadiusListener;

public abstract class Locatable {
	public Locatable()
	{
		// TODO: register self with location manager
	}
	
	abstract public GameTile location();
	abstract public int influenceRadius();
	abstract public void addRadiusListener( RadiusListener rl );
	abstract public void accept( LocatableVisitor lv );
	abstract public void instanceEntered( Instance i );
	abstract public void instanceExited( Instance i );
}
