package src.control;

/**
 * A Binding necessarily has a key and a meaning.  This is a relationship class.
 * 
 * @author Chris
 *
 */
public class Binding{
	
	private String meaning;
	private KeyCodeAndModifiers key;
	
	Binding(String meaning, KeyCodeAndModifiers key)
	{
		this.meaning = meaning;
		this.key = key;
	}
	public String meaning()
	{
		return meaning;
	}
	public KeyCodeAndModifiers key()
	{
		return key;
	}

}
