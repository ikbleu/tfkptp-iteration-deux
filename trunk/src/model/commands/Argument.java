package src.model.commands;

import src.model.control.Behavior;
import src.model.interfaces.ViewVisitor;
import src.model.interfaces.vArgument;

abstract public class Argument implements vArgument, Behavior {
	public Argument( String m )
	{
		meaning = m;
	}
	private String meaning;

	abstract public void execute();
	
	final public String meaning() {
		// TODO Auto-generated method stub
		return meaning;
	}
	
	final public String comparable()
	{
		return meaning();
	}
	
	public void accept( ViewVisitor v )
	{
		v.visitArgument( this );
	}
	
	final public String token()
	{
		return meaning();
	}
}
