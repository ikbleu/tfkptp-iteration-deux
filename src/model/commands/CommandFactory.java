package src.model.commands;

import src.model.Player;
import src.model.control.Behavior;
import src.model.control.Device;
import src.model.control.KeyEventInterpreterBuilder;
import src.model.instances.Instance;
import src.util.Hand;

public abstract class CommandFactory implements Device {
	public CommandFactory( Player p, String token, int numTicks )
	{
		hand = p.handFactory().make( Behavior.class );
		this.token = token;
		this.numTicks = numTicks;
	}
	private Hand< Behavior > hand;
	private String token;
	private int numTicks;
	
	abstract public Command makeCommand( Instance i );
	final public void setInstance( Instance i )
	{
		instance = i;
		hand.clear();
		doSetInstance( i );
	}
	abstract protected void doSetInstance( Instance i );
	private Instance instance;
	
	final public Instance instance()
	{
		return instance;
	}
	
	final public String token()
	{
		return token;
	}
	final public int numTicks()
	{
		return numTicks;
	}
	final public Hand< Behavior > hand()
	{
		return hand;
	}
	
	final public String context() { return "Command"; }
	final public String meaning() { return token(); }
	final public void direct(KeyEventInterpreterBuilder builder)
	{
		builder.behaviors( hand.spawnLens() );
	}
	
	final public void addArgument( Argument a )
	{
		hand.add( a );
	}
	
	final public void removeArgument( Argument a )
	{
		hand.remove( a );
	}
}
