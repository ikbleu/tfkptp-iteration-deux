package src.model.commands;

import src.model.instances.Instance;

abstract public class NoArgsCommandFactory extends CommandFactory {
	abstract public NoArgsCommand makeCommand(Instance i);
}
