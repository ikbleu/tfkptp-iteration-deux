package src.control;
import java.io.Serializable;


public class KeyCodeAndModifiers implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7449761297937265279L;
	
	private int keyCode;
	private int modifiers;
	
	KeyCodeAndModifiers(int keyCode, int modifiers)
	{
		this.keyCode = keyCode;
		this.modifiers = modifiers;
	}
	public int getKeyCode()
	{
		return keyCode;
	}
	public int getModifiers()
	{
		return modifiers;
	}
	public boolean equals(Object o)
	{
		if(o instanceof KeyCodeAndModifiers)
		{
			KeyCodeAndModifiers k = (KeyCodeAndModifiers) o;
			if(this.keyCode == k.getKeyCode() && this.modifiers == k.getModifiers())
				return true;
			else 
				return false;
		}
		return false;		
	}
	public String toString()
	{
		return keyCode +" "+ modifiers;
	}
}
