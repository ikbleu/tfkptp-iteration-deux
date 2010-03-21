package src.model.commands;

import src.model.control.Device;
import src.model.control.KeyEventInterpreterBuilder;
import src.model.instances.Instance;

public abstract class CommandFactory implements Device {
	abstract public Command makeCommand( Instance i );
	abstract public String token();
	
	public String context() { return null; }
	public String meaning() { return null; }
	public void direct(KeyEventInterpreterBuilder builder) {}
}
