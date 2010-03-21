package src.model.commands;

import src.model.control.Behavior;
import src.model.instances.Instance;

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
}
