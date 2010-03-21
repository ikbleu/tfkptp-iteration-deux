package src.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import src.model.commands.Command;
import src.model.commands.CommandListener;
import src.model.commands.CommandSender;
import src.model.interfaces.InstanceHolder;

public class Player implements CommandSender {
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
}
