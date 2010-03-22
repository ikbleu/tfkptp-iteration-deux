package src.model.interfaces;

import src.model.exceptions.YoureDoingItWrongException;

public interface Clock
{
	public void remove(Tickable ticker);
	public void start();
    
    public void stop();
	public void register(String tierOrderToken, Tickable ticker) throws YoureDoingItWrongException;

}
