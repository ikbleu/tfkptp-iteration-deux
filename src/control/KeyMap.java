package src.control;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import src.control.interfaces.KeyMapVisitor;
import src.control.interfaces.KeyMapInterface;
import src.control.interfaces.EditableKeyConfig;
import src.control.interfaces.BindingMapBuilder;
import src.control.interfaces.BindingMapDirector;

/**
 * KeyMap needs to be able to return a meaning from (1. a context String and 2. KeyCodeAndModifiers instance.)
 * It stores a Map from each context an associated list of bindings for that context.
 * @author Chris
 *
 */
public class KeyMap implements 
	BindingMapBuilder, 
	BindingMapDirector, 
	EditableKeyConfig,
	KeyMapInterface
	{
	
	//These variables represent the configuration of all the controls in the game.

	private Map< String, List<Binding> > contextToBindings;
    private MahBuilder mahbuilder;

    KeyMap() {
        this.contextToBindings = new Hashtable<String,List<Binding>>();
        this.mahbuilder = new MahBuilder(this.contextToBindings);
    }
    /**
     * @author kagioglu
     */
	private static class MahBuilder implements BindingMapBuilder {
        private final Map<String,List<Binding>> map;
        private String currentContext;
        private boolean building;
        MahBuilder(Map<String,List<Binding>> map) {
            this.map = map;
            this.currentContext = null;
            this.building = false;
        }
        public void start() {
            if(this.building) { throw new RuntimeException("misuse!"); }
            else { this.building = true; this.map.clear(); }
        }
        public void context(String context) {
            if(this.building) {
                this.currentContext = context;
                this.map.put(context, new ArrayList<Binding>());
            }
            else { throw new RuntimeException("misuse"); }
        }
        public void binding(KeyCodeAndModifiers event, String meaning) {
            if(this.building) {
                this.map.get(this.currentContext).add(new Binding(meaning, event));
            }
            else { throw new RuntimeException("misuse"); }
        }
        public void end() {
            if(this.building) { this.currentContext = null; this.building = false; }
            else { throw new RuntimeException("misuse"); }
        }
    }

	public void start() { this.mahbuilder.start(); }
    public void end() { this.mahbuilder.end(); }

    /**
	 * This method adds a binding (key to meaning) to the current Builder context.
     * @author kagioglu
	 */
	public void binding(KeyCodeAndModifiers event, String meaning) {
		this.mahbuilder.binding(event, meaning);
	}

	/**
	 * This method sets the context for a BindingMapBuilder. Once the context is set,
	 * Then when binding() is called, it is added to that set context.
     * @author kagioglu
	 */
	public void context(String context) {
		this.mahbuilder.context(context);
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

    @Override
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

	public void getMeaning(String context, KeyMapVisitor visitor,
			KeyCodeAndModifiers key) {
		boolean found = false;
		if(contextToBindings.containsKey(context))
		{
		//Search for the given context
		//if context found, then search Bindings for a meaning match for the key
			List<Binding> l = contextToBindings.get(context);
			Iterator<Binding> i = l.iterator();
			while(i.hasNext())
			{
				Binding b = i.next();
				if(b.key()==key)
				{
					visitor.foundMeaning(b.meaning());
					found = true;
				}
			}
			if(!found)
				visitor.meaningUnknown();		
		}
		else
			visitor.contextUnknown();
	}
}
