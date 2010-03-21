package src.control;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.Map;

/**
 * wrapper class so we can save RepToKey to a file as an object.
 * @author Chris
 *
 */
public class RepToKey implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5674830180352794571L;
	private Map< String, KeyCodeAndModifiers > repToKey = new Hashtable< String, KeyCodeAndModifiers >();

	RepToKey(Map< String, KeyCodeAndModifiers > repToKey ){
		this.repToKey =repToKey;
	}
	public Map< String, KeyCodeAndModifiers > repToKey(){
		return repToKey;
	}
	
}
