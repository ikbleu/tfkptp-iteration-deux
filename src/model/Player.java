package src.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import src.model.instances.Command;
import src.model.instances.CommandListener;
import src.model.instances.CommandSender;

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
		for ( CommandListener cl : commandListeners.get( c.token() ) )
			cl.commandOccurred( c );
	}
}
