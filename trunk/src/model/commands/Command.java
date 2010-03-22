package src.model.commands;

import src.model.instances.Instance;
import src.model.interfaces.GameTile;

public abstract class Command {
	public Command( String token, Instance i, int numTicks )
	{
		this( token, i, numTicks, false );
	}
	public Command( String token, Instance i, int numTicks, boolean inst )
	{
		this.token = token;
		this.numTicks = numTicks;
		instance = i;
		isInstant = inst;
	}
	private Instance instance;
	private String token;
	private String when = "execute"; 
	private int numTicks;
	private boolean isInstant;
	
	final public boolean isInstant()
	{
		return isInstant;
	}
	
	final public String token()
	{
		return token;
	}
	
	final public Instance instance()
	{
		return instance;
	}
	
	public GameTile location()
	{
		return instance.location(); // TODO: uncomment this line
	}
	
	final public int numTicks()
	{
		return numTicks;
	}
	
	final public String when()
	{
		return when;
	}
	
	final public void setWhen( String w )
	{
		when = w;
	}
	
	abstract public void accept( CommandVisitor c );
}
