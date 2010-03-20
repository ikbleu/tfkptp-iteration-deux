package src.model.instances;

import src.model.interfaces.GameTile;

public interface Command {
	public String token();
	public Instance instance();
	public void accept( CommandVisitor c );
	public GameTile location();
}
