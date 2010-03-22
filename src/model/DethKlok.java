package src.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import src.model.exceptions.YoureDoingItWrongException;
import src.model.interfaces.Clock;
import src.model.interfaces.Tickable;

public class DethKlok implements Clock
{
    private Map<String, Set<Tickable> > collection;
    private Timer timer;
    private long msecsPerTick;
    private final List<String> orderedList;
    
    public DethKlok( List<String> possibleValues, long msecs ) throws YoureDoingItWrongException
    {
    	for (int i = 0; i < possibleValues.size(); i++)
    		for (int j = i + 1; j < possibleValues.size(); j++)
    			if (possibleValues.get(i).equals(possibleValues.get(j)))
    				throw new YoureDoingItWrongException("Can't have duplicate values inside the clock's tier list");
    	
    	orderedList = possibleValues;
        collection = new Hashtable<String, Set<Tickable> >();
        this.initializeTimer();
        msecsPerTick = msecs;
    }
    
    private void initializeTimer()
    {
        this.timer = new Timer( "MURMAIDER MURMAIDER" );
    }
    
    private void initializeTask()
    {
        class DethTask extends TimerTask
        {
            private final Map<String, Set<Tickable> >  plisteners;
            private final List<String> order;
            
            public DethTask( List<String> order, Map<String, Set<Tickable> > plisteners)
            {
                this.plisteners = plisteners;
                this.order = order;
            }
            
            public void run()
            {
                synchronized ( this.plisteners )
                {
                    
                }
            }
        }
        
        this.timer.scheduleAtFixedRate( new DethTask( this.orderedList, this.collection), msecsPerTick, msecsPerTick );
    }
    
    
    public void start()
    {
        this.timer.cancel();
        this.initializeTimer();
        this.initializeTask();
    }
    
    public void stop()
    {
        this.timer.cancel();
    }

	@Override
	public void register(String tierOrderToken, Tickable ticker) throws YoureDoingItWrongException
	{
		// TODO Auto-generated method stub
		synchronized ( this.collection )
        {
            if (!this.orderedList.contains(tierOrderToken))
            	throw new YoureDoingItWrongException("Invalid tier ID");
            
            Set<Tickable> s;
            if (this.collection.containsKey(tierOrderToken))
            	s = collection.get(tierOrderToken);
            else
            	s = new HashSet<Tickable>();
            
            s.add(ticker);
            collection.put(tierOrderToken, s);
        }
	}

	@Override
	public void remove(Tickable ticker)
	{
		// TODO Auto-generated method stub
		synchronized ( this.collection )
        {
            for (int i = 0; i < orderedList.size(); i++)
            {
            	if (collection.get(orderedList.get(i)).contains(ticker))
            	{
            		collection.get(orderedList.get(i)).remove(ticker);
            		break;
            	}
            }
        }
	}
}