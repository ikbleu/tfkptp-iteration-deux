package src.control.interfaces;

import java.util.List;

public interface DisplayableKeyMap {
	
	/**
	 * @return the current DisplayableKeyMap context String.
	 */
	public String context();
	
	/**
	 * 
	 * @return true if there is another context. False if none. 
	 */
	public boolean hasNext();
	
	/**
	 * changes the current context to the next one.
	 */
	public void nextContext();
	
	/**
	 * 
	 * @return list of DisplayableBindings for a given context. 
	 */
	public List<DisplayableBinding> getBindingList();
}
