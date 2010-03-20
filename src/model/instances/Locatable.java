package src.model.instances;

import src.model.interfaces.GameTile;
import src.model.interfaces.LocatableVisitor;
import src.model.interfaces.RadiusListener;

public abstract class Locatable {
	private GameTile location;
	public Locatable( GameTile g )
	{
		location = g;
		// TODO: register self with location manager
	}
	
	final public GameTile location()
	{
		return location;
	}
	
	final protected void setLocation( GameTile g )
	{
		GameTile prev = location;
		location = g;
		updateLocation( prev );
	}
	abstract protected void updateLocation( GameTile prev );
	
	abstract public int influenceRadius();
	abstract public void addRadiusListener( RadiusListener rl );
	abstract public void accept( LocatableVisitor lv );
	abstract public void instanceEntered( Instance i );
	abstract public void instanceExited( Instance i );
	abstract public String token();
}
