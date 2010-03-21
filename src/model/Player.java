package src.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import src.model.commands.Command;
import src.model.commands.CommandListener;
import src.model.commands.CommandSender;
import src.model.control.Device;
import src.model.interfaces.GameTile;
import src.model.interfaces.InstanceHolder;
import src.util.Hand;
import src.util.HandFactory;
import src.util.Lens;

public class Player implements CommandSender
{
	GameMap map;
	GameTile startingLocation;
        ResourceManager rscManager;
	
	public Player( boolean isH, HandFactory hF, GameMap m, GameTile startLoc,
                ResourceManager rm)
	{
		isHuman = isH;
		handFactory = hF;
		map = m;
		startingLocation = startLoc;
		myHand = handFactory.make( Device.class );
                rscManager = rm;
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
		// TODO Auto-generated method stub
		return null;
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
}
