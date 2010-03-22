package src.model;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import src.model.interfaces.Clock;
import src.model.interfaces.Tickable;

public class DethKlok implements Clock
{
    private final Collection< Tickable > primaryCollection, secondaryCollection;
    private Timer timer;
    private long msecsPerTick;
    
    public DethKlok( long msecs ) {
        this.primaryCollection = new LinkedList< Tickable >();
        this.secondaryCollection = new LinkedList< Tickable >();
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
            private final Collection< Tickable > plisteners, slisteners;
            
            public DethTask( Collection<Tickable> plisteners, Collection<Tickable> slisteners) {
                this.plisteners = plisteners;
                this.slisteners = slisteners;
            }
            
            public void run()
            {
                synchronized ( this.plisteners )
                {
                    for ( Tickable listener : plisteners )
                    {
                        listener.tick();
                    }
                }
                
                synchronized ( this.slisteners )
                {
                    for ( Tickable listener : slisteners )
                    {
                        listener.tick();
                    }
                }
            }
        }
        
        this.timer.scheduleAtFixedRate( new DethTask( this.primaryCollection, this.secondaryCollection ),
                                        msecsPerTick, msecsPerTick );
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
	public void registerPrimary(Tickable ticker)
	{
		// TODO Auto-generated method stub
		synchronized ( this.primaryCollection )
        {
            this.primaryCollection.add( ticker );
        }
	}

	@Override
	public void registerSecondary(Tickable ticker)
	{
		// TODO Auto-generated method stub
		synchronized ( this.secondaryCollection )
        {
            this.secondaryCollection.add( ticker );
        }
		
	}

	@Override
	public void remove(Tickable ticker)
	{
		// TODO Auto-generated method stub
		synchronized ( this.primaryCollection )
        {
            this.primaryCollection.remove( ticker );
        }
		
		synchronized ( this.secondaryCollection )
        {
            this.secondaryCollection.remove( ticker );
        }
	}
}