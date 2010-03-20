package src.model.commands;

import src.model.instances.Instance;

public abstract class CommandFactory {
	abstract public Command makeCommand( Instance i );
}
