package src.model.instances;

import java.util.LinkedList;
import java.util.List;

import src.model.AoEManager;
import src.model.interfaces.GameTile;
import src.model.interfaces.LocatableVisitor;
import src.model.interfaces.RadiusListener;

import java.io.Serializable;

public abstract class Locatable implements Serializable
{
	private GameTile location;
	public Locatable( GameTile g )
	{
		location = g;
		AoEManager.instance().registerListening( this );
	}
	
	final public GameTile location()
	{
		return location;
	}
	
	final protected void setLocation( GameTile g )
	{
		location = g;
	}
	
	private List< RadiusListener > radiusListeners = new LinkedList< RadiusListener >();
	final public void addRadiusListener( RadiusListener cl )
	{
		radiusListeners.add( cl );
	}
	final public void removeRadiusListener( RadiusListener cl )
	{
		radiusListeners.remove( cl );
	}
	
	private int influenceRadius = 0;
	final protected void setInfluenceRadius( int rad )
	{
		int prev = influenceRadius;
		influenceRadius = rad;
		for ( RadiusListener rl : radiusListeners )
			rl.radiusChanged( this, prev );
	}
	
	final public int influenceRadius()
	{
		return influenceRadius;
	}
	
	final public void destroy()
	{
		doDestruction();
		AoEManager.instance().unregisterListening( this );
	}
	protected void doDestruction() {}
	
	abstract public void accept( LocatableVisitor lv );
	abstract public void entered( Instance l );
	abstract public void exited( Instance l );
	abstract public String token();
}
