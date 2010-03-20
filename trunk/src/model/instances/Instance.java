package src.model.instances;

import java.util.LinkedList;
import java.util.List;

import src.model.interfaces.vInstance;

public abstract class Instance implements vInstance, CommandSender
{
	private List< CommandListener > commandListeners = new LinkedList< CommandListener >();
	final public void registerCommandListener( CommandListener cl )
	{
		commandListeners.add( cl );
	}
	
	final public void executeCommand( Command c )
	{
		for ( CommandListener cl : commandListeners )
			cl.commandOccurred( c );
	}
}
