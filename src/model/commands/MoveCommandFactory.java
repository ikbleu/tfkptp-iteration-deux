package src.model.commands;

import src.model.instances.Instance;

abstract public class MoveCommandFactory extends CommandFactory {
	abstract public MoveCommand makeCommand(Instance i);
}
