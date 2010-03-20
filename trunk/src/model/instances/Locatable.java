package src.model.instances;

import java.util.LinkedList;
import java.util.List;

import src.model.LocationManager;
import src.model.interfaces.GameTile;
import src.model.interfaces.LocatableVisitor;
import src.model.interfaces.MovementListener;
import src.model.interfaces.RadiusListener;

public abstract class Locatable {
	private GameTile location;
	public Locatable( GameTile g )
	{
		location = g;
		LocationManager.instance().register( this );
	}
	
	final public GameTile location()
	{
		return location;
	}
	
	private List< MovementListener > moveListeners = new LinkedList< MovementListener >();
	final public void addMovementListener( MovementListener ml )
	{
		moveListeners.add( ml );
	}
	final public void removeMovementListener( MovementListener ml )
	{
		moveListeners.remove( ml );
	}
	
	final protected void setLocation( GameTile g )
	{
		GameTile prev = location;
		location = g;
		for ( MovementListener ml : moveListeners )
			ml.locationChanged( this, prev );
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
		LocationManager.instance().unregister( this );
	}
	protected void doDestruction() {}
	
	abstract public void accept( LocatableVisitor lv );
	abstract public void entered( Locatable l );
	abstract public void exited( Locatable l );
	abstract public String token();
}