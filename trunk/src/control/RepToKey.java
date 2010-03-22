package src.control;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

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
	public String toString()
	{
		String toReturn="";
		Set<Entry<String, KeyCodeAndModifiers>>  s = repToKey.entrySet();
		Iterator<Entry<String,KeyCodeAndModifiers>> i = s.iterator();
		while(i.hasNext())
		{
			Entry<String,KeyCodeAndModifiers> e = i.next();
			toReturn=toReturn.concat(e.getKey()+"\t\t"+e.getValue()+'\n');
		}
		toReturn = toReturn+'\n';
		return toReturn;
	}
	
}
