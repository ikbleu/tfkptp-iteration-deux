package src.model.instances;

public interface CommandSender {
	public void executeCommand( Command c );
	public void addCommandListener( CommandListener cl );
	public void removeCommandListener( CommandListener cl );
}
