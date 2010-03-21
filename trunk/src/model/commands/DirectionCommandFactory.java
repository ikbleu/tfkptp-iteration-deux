package src.model.commands;

import src.model.instances.Instance;

abstract public class DirectionCommandFactory extends CommandFactory {
	abstract public DirectionCommand makeCommand(Instance i);
}