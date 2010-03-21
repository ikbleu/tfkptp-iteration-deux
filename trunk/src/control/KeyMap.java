package src.control;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import src.control.interfaces.*;

/**
 * KeyMap needs to be able to return a meaning from (1. a context String and 2. KeyCodeAndModifiers instance.)
 * It stores a Map from each context an associated list of bindings for that context.
 * @author Chris
 *
 */
public class KeyMap implements 
	BindingMapBuilder, 
	BindingMapDirector, 
	EditableKeyConfig
	{
	
	//These variables represent the configuration of all the controls in the game.  
	
	private Map< String, List<Binding> > contextToBindings = new Hashtable< String, List<Binding> >();

	private String currentBuilderContext;
	@Override
	/**
	 * This method adds a binding (key to meaning) to the current Builder context. 
	 */
	public void binding(KeyCodeAndModifiers event, String meaning) {
		//
		if(!contextToBindings.containsKey(currentBuilderContext))
			contextToBindings.get(meaning).add(new Binding(meaning, event));
		else
			System.out.println("KeyMap context either not found or not set!");
		
	}

	@Override
	/**
	 * This method sets the context for a BindingMapBuilder. Once the context is set,
	 * Then when binding() is called, it is added to that set context. 
	 */
	public void context(String context) {
		contextToBindings.put(context, new ArrayList<Binding>());
		currentBuilderContext = context;
		
	}

	@Override
	public void buildAll(BindingMapBuilder builder) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void buildForContext(Iterator<String> contexts,
			BindingMapBuilder builder) {
		// TODO Auto-generated method stub
		
	}

	@Override
	/**
	 * This method adds a context to the KeyMap. 
	 */
	public boolean addContext(String context) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	/**
	 * This method adds a meaning to key binding under a specific context 
	 */
	public boolean bindMeaning(String context, String meaning,
			KeyCodeAndModifiers event) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	/**
	 * This method removes a context from the map. 
	 */
	public boolean removeContext(String context) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	/**
	 * For a given context, it unbinds the KeyCodeAndModifiers key from the binding containing specified meaning. 
	 */
	public boolean unbindMeaning(String context, String meaning) {
		// TODO Auto-generated method stub
		return false;
	}


	
}
