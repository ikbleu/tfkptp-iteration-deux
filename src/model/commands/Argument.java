package src.model.commands;

import src.model.control.Behavior;

abstract public class Argument implements Behavior {
	public Argument( String m )
	{
		meaning = m;
	}
	private String meaning;

	@Override
	abstract public void execute();
	
	final public String meaning() {
		// TODO Auto-generated method stub
		return meaning;
	}
	
	final public String comparable()
	{
		return meaning();
	}
}
