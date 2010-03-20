package src.model.commands;

import src.model.instances.Instance;
import src.model.interfaces.GameTile;

public interface Command {
	public String token();
	public Instance instance();
	public void accept( CommandVisitor c );
	public GameTile location();
	public int numTicks();
}
