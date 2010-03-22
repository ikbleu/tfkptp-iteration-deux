package src.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import src.model.commands.Command;
import src.model.commands.CommandListener;
import src.model.commands.CommandSender;
import src.model.control.Device;
import src.model.instances.Instance;
import src.model.instances.InstanceExistenceListener;
import src.model.interfaces.Clock;
import src.model.interfaces.GameTile;
import src.model.interfaces.InstanceHolder;
import src.model.interfaces.SakuraMap;
import src.util.Hand;
import src.util.HandFactory;
import src.util.Lens;

public class Player implements CommandSender, InstanceExistenceListener
{
	GameMap map;
	GameTile startingLocation;
        ResourceManager rscManager;
        VisibilityManager visManager;
	
	public Player( boolean isH, HandFactory hF, GameMap m, GameTile startLoc,
                ResourceManager rm, Clock c)
	{
		isHuman = isH;
		handFactory = hF;
		map = m;
		startingLocation = startLoc;
		myHand = handFactory.make( Device.class );
        rscManager = rm;
        visManager = new VisibilityManager(this, map, HasPlayerManager.getInstance(), c);  
        
        Instance.addGlobalInstanceExistenceListener( this );
	}
	private Hand< Device > myHand;
	
	private boolean isHuman;
	
	public boolean isHuman()
	{
		return isHuman;
	}
	
	private HandFactory handFactory;
	
	public HandFactory handFactory()
	{
		return handFactory;
	}
	
	private Map< String, List< CommandListener > > commandListeners = new HashMap< String, List< CommandListener > >();
	final public void addCommandListener( String token, CommandListener cl )
	{
		if ( ! commandListeners.containsKey( token ) )
			commandListeners.put( token, new LinkedList< CommandListener >() );
		commandListeners.get( token ).add( cl );
	}
	final public void removeCommandListener( String token, CommandListener cl )
	{
		if ( commandListeners.containsKey( token ) )
			commandListeners.get( token ).remove( cl );
	}
	
	final public void executeCommand( Command c )
	{
		List< CommandListener > ls = commandListeners.get( c.token() );
		if ( ls != null )
			for ( CommandListener cl : ls )
				cl.commandOccurred( c );
	}
	public InstanceHolder instanceHolder() {
		return AoEManager.instance();
	}
	
	public void addDevice( Device d )
	{
		myHand.add( d );
	}
	
	public void removeDevice( Device d )
	{
		myHand.remove( d );
	}
	
	public Lens< Device > lens()
	{
		return myHand.spawnLens();
	}
	
	public GameTile startingLocation()
	{
		return startingLocation;
	}

    public Map<String, Integer> resourceCount()
    {
        return rscManager.getAllAmounts();
    }
    
    public SakuraMap getVisibilityManager()
    {
    	return visManager;
    }
    
    private List< InstanceExistenceListener > iels = new CopyOnWriteArrayList< InstanceExistenceListener >();
    public void addInstanceExistenceListener( InstanceExistenceListener iel )
    {
    	iels.add( iel );
    }
    public void removeInstanceExistenceListener( InstanceExistenceListener iel )
    {
    	iels.remove( iel );
    }
	
	public void delInstance(Instance i) {
		if ( i.getPlayer() == this )
			for ( InstanceExistenceListener iel : iels )
				iel.delInstance( i );
	}
	
	public void newInstance(Instance i) {
		if ( i.getPlayer() == this )
			for ( InstanceExistenceListener iel : iels )
				iel.newInstance( i );
	}
	
	public VisibilityManager vMan()
	{
		return visManager;
	}
}
