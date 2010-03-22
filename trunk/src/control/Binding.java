package src.control;

import src.control.interfaces.DisplayableBinding;

/**
 * A Binding necessarily has a key and a meaning.  This is a relationship class.
 * 
 * @author Chris
 *
 */
public class Binding implements DisplayableBinding{
	
	private String meaning;
	private KeyCodeAndModifiers key;
	private Translator trans = new Translator();
	
	Binding(String meaning, KeyCodeAndModifiers key)
	{
		this.meaning = meaning;
		this.key = key;
	}
	public String meaning()
	{
		return meaning;
	}
	public String transKey()
	{
		return trans.toString(key);
	}
	public KeyCodeAndModifiers key()
	{
		return key;
	}
	public void setMeaning(String meaning){
		this.meaning = meaning;
	}
	public void setKey(KeyCodeAndModifiers key){
		this.key = key;
	}
	public String toString()
	{
		return meaning + "\t" +trans.toString(key);
	}

}
