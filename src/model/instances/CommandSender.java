package src.model.instances;

public interface CommandSender {
	public void executeCommand( Command c );
	public void addCommandListener( String token, CommandListener cl );
	public void removeCommandListener( String token, CommandListener cl );
}
