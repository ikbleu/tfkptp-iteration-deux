package src.model.commands;

import src.model.instances.Instance;

abstract public class RallyPointCommandFactory extends CommandFactory {
	abstract public RallyPointCommand makeCommand(Instance i);
}
