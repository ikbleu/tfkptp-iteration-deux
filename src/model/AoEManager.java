/*
 * file: AoEManager.java
 */

package src.model;

import src.model.interfaces.InstanceFunction;
import src.model.interfaces.InstanceHolder;
import src.model.interfaces.MovementListener;
import src.model.interfaces.RadiusListener;
import src.model.interfaces.GameTile;
import src.model.instances.Locatable;
import src.model.instances.Instance;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 * Manages the location of locatable game objects and allows objects to register
 * to receive updates about objects that move into or out of its influence
 * radius.
 *
 * @author Christopher Dudley
 */
public class AoEManager implements MovementListener, RadiusListener, InstanceHolder
{
    private Map<GameTile, List<Instance>> whosThere;
    private Map<GameTile, List<Locatable>> whosListening;
    
    final private boolean DEBUGGING = true; // TODO: remove references to this

    private List<Locatable> thingsListening;

    /*
     * Creates a new AoEManager with no objects on any tiles and no objects
     * registered for movement updates.
     */
    private AoEManager()
    {
        whosThere = new HashMap<GameTile, List<Instance>>();
        whosListening = new HashMap<GameTile, List<Locatable>>();

        thingsListening = new ArrayList<Locatable>();
    }
    
    private static AoEManager instance = null;
    public static AoEManager instance()
    {
    	if ( instance == null )
    		instance = new AoEManager();
    	return instance;
    }

    public void registerLocation(Instance thing)
    {
    	if ( DEBUGGING ) return;
        registerLocation(thing, thing.location());
    }

    private void registerLocation(Instance thing, GameTile location)
    {
        List<Instance> onTile = whosThere.get(location);

        if(onTile == null)
        {
            onTile = new ArrayList<Instance>();
            whosThere.put(location, onTile);
        }

        onTile.add(thing);

        // Let stuff know that the thing is now inside their radii!
        List<Locatable> listeningThere = whosListening.get(location);

        if(listeningThere == null)
        {
            listeningThere = new ArrayList<Locatable>();
            whosListening.put(location, listeningThere);
        }

        for(Locatable l : listeningThere)
        {
            if(l != thing)
                l.entered(thing);
        }
    }

    public void registerListening(Locatable thing)
    {
    	if ( DEBUGGING ) return;
        if(thingsListening.contains(thing))
            throw new RuntimeException("Already registered for listening!");

        thingsListening.add(thing);

        GameTile location = thing.location();
        int radius = thing.influenceRadius();

        List<GameTile> inRadius = location.getTilesAround(radius);

        for(GameTile gt : inRadius)
        {
            List<Locatable> listeningThere = whosListening.get(gt);

            if(listeningThere == null)
            {
                listeningThere = new ArrayList<Locatable>();
                whosListening.put(gt, listeningThere);
            }

            listeningThere.add(thing);
        }
    }

    public void unregisterLocation(Instance thing)
    {
    	if ( DEBUGGING ) return;
        unregisterLocation(thing, thing.location());
    }

    private void unregisterLocation(Instance thing, GameTile location)
    {
        List<Instance> onTile = whosThere.get(location);

        if(onTile == null || !onTile.contains(thing))
            throw new RuntimeException("Can't unregister something that's not registered!");

        onTile.remove(thing);

        // Let stuff know that the thing is now outside their radii!
        List<Locatable> listeningThere = whosListening.get(location);

        if(listeningThere == null)
        {
            listeningThere = new ArrayList<Locatable>();
            whosListening.put(location, listeningThere);
        }

        for(Locatable l : listeningThere)
        {
            if(l != thing)
                l.exited(thing);
        }
    }

    public void unregisterListening(Locatable thing)
    {
    	if ( DEBUGGING ) return;
        unregisterListening(thing, thing.location(), thing.influenceRadius());
    }

    private void unregisterListening(Locatable thing, GameTile location, int radius)
    {
        if(!thingsListening.contains(thing))
            throw new RuntimeException("Thing not registered for listening!");

        thingsListening.remove(thing);

        List<GameTile> inRadius = location.getTilesAround(radius);

        for(GameTile gt : inRadius)
        {
            List<Locatable> listeningThere = whosListening.get(gt);

            if(listeningThere == null || !listeningThere.contains(thing))
            {
                throw new RuntimeException("Thing wasn't registered to listen within its radius.");
            }

            listeningThere.remove(thing);
        }
    }

    /**
     * Updates the instance's location from the given previous location to its
     * new location and notify any objects whose influence radius the moving
     * instance has either left or entered.
     *
     * @param thing the instnace object that has moved.
     * @param prev the previous location of the object.
     */
    public void locationChanged(Instance thing, GameTile prev)
    {
        GameTile next = thing.location();
        boolean registeredListener = thingsListening.contains(thing);

        unregisterLocation(thing, prev);

        if(registeredListener)
            unregisterListening(thing, prev, thing.influenceRadius());

        registerLocation(thing);

        if(registeredListener)
            registerListening(thing);
    }

    /**
     * Once notified that a thing has changed its radius, unregisters from
     * tiles no longer being listened to and registers to new tiles being
     * listened to.
     *
     * @param thing the locatable who's influence radius has changed.
     * @param prevRadius the previous influence radius.
     */
    public void radiusChanged(Locatable thing, int prevRadius)
    {
        GameTile location = thing.location();

        unregisterListening(thing, location, prevRadius);
        registerListening(thing);
    }

	@Override
	public void applyToAll(InstanceFunction iF, Player p)
	{
		// TODO Auto-generated method stub
		
	}
}
