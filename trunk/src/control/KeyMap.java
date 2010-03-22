package src.control;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import src.control.interfaces.*;
import src.model.control.BindingMapBuilder;
import src.model.control.BindingMapDirector;

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
		//System.out.println("binding called. CurrentContext:"+currentBuilderContext);
		if(contextToBindings.containsKey(currentBuilderContext))
		{
			Binding b = new Binding(meaning,event);
			contextToBindings.get(currentBuilderContext).add(b);
			//System.out.println("added binding:"+b);
		}
		else{}
			//System.out.println("KeyMap context "+currentBuilderContext+"already in contextToBindings.");
		
	}

	@Override
	/**
	 * This method sets the context for a BindingMapBuilder. Once the context is set,
	 * Then when binding() is called, it is added to that set context. 
	 */
	public void context(String context) {
		contextToBindings.put(context, new ArrayList<Binding>());
		currentBuilderContext = context;
	//	System.out.println("Made new context "+context+" in map with its own empty list of bindings.");
		
	}

	@Override
	/**
	 * This method should initialize the entire KeyMap from a BindingMapBuilder
     * @author kagioglu
	 */
	public void buildAll(BindingMapBuilder builder) {
		for(Map.Entry<String,List<Binding>> con : this.contextToBindings.entrySet()) {
            builder.context(con.getKey());
            for(Binding b : con.getValue()) {
                builder.binding(b.key(), b.meaning());
            }
        }
	}

	@Override
	/**
	 * Adds certain bindings for a set of contexts.
     * @author kagioglu
	 */
	public void buildForContext(Iterator<String> contexts,
			BindingMapBuilder builder) {
		while(contexts.hasNext()) {
            String con = contexts.next();
            builder.context(con);
            for(Binding b : this.contextToBindings.get(con)) {
                builder.binding(b.key(), b.meaning());
            }
        }
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
	public String toString()
	{
		//System.out.println("In toString:");
		String toReturn="";
		Set<Entry<String, List<Binding>>>  s = contextToBindings.entrySet();
		Iterator<Entry<String,List<Binding>>> i = s.iterator();
		while(i.hasNext())
		{
			//System.out.println(toReturn);
			Entry<String,List<Binding>> e = i.next();
			toReturn = toReturn.concat("begincontext\n");
			toReturn=toReturn.concat(e.getKey()+'\n');
			//System.out.println(toReturn);
			Iterator<Binding> bindingListIterator = e.getValue().iterator();
			while(bindingListIterator.hasNext()) 
			{
				Binding b = bindingListIterator.next();
				toReturn = toReturn.concat(b.toString())+'\n';
				//System.out.println("Binding:"+toReturn);
			}
			toReturn=toReturn.concat("endcontext\n");
		}
		return toReturn;
	}


	
}
