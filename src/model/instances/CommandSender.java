package src.model.instances;

public interface CommandSender {
	public void executeCommand( Command c );
	public void registerCommandListener( CommandListener cl );
}
