/*
 * file: LocationManager.java
 */

package src.model;

import src.model.interfaces.MovementListener;
import src.model.interfaces.RadiusListener;
import src.model.interfaces.GameTile;
import src.model.instances.Locatable;

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
public class LocationManager implements MovementListener, RadiusListener
{
    private Map<GameTile, List<Locatable>> whosThere;
    private Map<GameTile, List<Locatable>> whosListening;

    /*
     * Creates a new LocationManager with no objects on any tiles and no objects
     * registered for movement updates.
     */
    private LocationManager()
    {
        whosThere = new HashMap<GameTile, List<Locatable>>();
        whosListening = new HashMap<GameTile, List<Locatable>>();
    }
    
    private static LocationManager instance = null;
    public static LocationManager instance()
    {
    	if ( instance == null )
    		instance = new LocationManager();
    	return instance;
    }

    /**
     * Updates the instance's location from the given previous location to its
     * new location and notify any objects whose influence radius the moving
     * instance has either left or entered.
     *
     * @param loc the locatable object that has moved.
     * @param prev the previous location of the object.
     */
    public void locationChanged(Locatable loc, GameTile prev)
    {
        // Move the locatable within the internal lists of who's on what tile
        List<Locatable> prevList = whosThere.get(prev);

        if(prevList == null)
            throw new RuntimeException("There should have been some units here");

        prevList.remove(loc);

        GameTile next = loc.location();

        List<Locatable> nextList = whosThere.get(next);

        if(nextList == null)
        {
            nextList = new ArrayList<Locatable>();
            whosThere.put(next, nextList);
        }

        nextList.add(loc);

        // Update the moving object's own influence radius if it was registered
        // for updates for that.
        List<GameTile> prevRadius = MapSpacialManager.getTilesAround(prev, loc.influenceRadius());
        List<GameTile> nextRadius = MapSpacialManager.getTilesAround(prev, loc.influenceRadius());

        // Check for tiles that are no longer in loc's radius.
        for(GameTile gt : prevRadius)
        {
            if(!nextRadius.contains(gt))
            {
                // gt is no longer in the object's influence radius.
                List<Locatable> exitting = whosThere.get(gt);

                for(Locatable l : exitting)
                {
                    if(loc != l)
                        loc.exited(l);
                }

                // Remove loc from listening at this location.
                List<Locatable> listeningHere = whosListening.get(gt);
                if(!listeningHere.remove(loc))
                    throw new RuntimeException("Loc should have been listening to this tile.");
            }
        }

        // Check for tiles now in loc's radius.
        for(GameTile gt : nextRadius)
        {
            if(!prevRadius.contains(gt))
            {
                // gt is now in loc's influence radius.
                List<Locatable> entering = whosThere.get(gt);

                for(Locatable l : entering)
                {
                    if(loc != l)
                        loc.entered(l);
                }

                // Add loc to listening at this location
                List<Locatable> listeningHere = whosListening.get(gt);
                listeningHere.add(loc);
            }
        }

        // Check which influence radii have been moved out of or into.
        List<Locatable> prevListeners = whosListening.get(prev);
        List<Locatable> nextListeners = whosListening.get(next);

        for(Locatable l : prevListeners)
        {
            if(loc != l && !nextListeners.contains(l))
            {
                // loc exitting l's influence radius.
                l.exited(loc);
            }
        }

        for(Locatable l : nextListeners)
        {
            if(loc != l && !prevListeners.contains(l))
            {
                // loc is entering l's influence radius.
                l.entered(loc);
            }
        }
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
        int newRadius = thing.influenceRadius();
        GameTile location = thing.location();
        List<GameTile> oldZone = MapSpacialManager.getTilesAround(location, prevRadius);
        List<GameTile> newZone = MapSpacialManager.getTilesAround(location, newRadius);

        if(newRadius > prevRadius)
        {
            for(GameTile gt : newZone)
            {
                if(!oldZone.contains(gt))
                {
                    // thing now listening to this tile as well.
                    List<Locatable> listeningHere = whosListening.get(gt);

                    if(listeningHere == null)
                    {
                        listeningHere = new ArrayList<Locatable>();
                        whosListening.put(gt, listeningHere);
                    }

                    listeningHere.add(thing);

                    // Let thing know about objects on this tile.
                    List<Locatable> stuffHere = whosThere.get(gt);

                    if(stuffHere == null)
                    {
                        stuffHere = new ArrayList<Locatable>();
                        whosThere.put(gt, stuffHere);
                    }

                    for(Locatable l : stuffHere)
                    {
                        if(l != thing)
                            thing.entered(l);
                    }
                }
            }
        }
        else if(prevRadius > newRadius)
        {
            for(GameTile gt : oldZone)
            {
                if(!newZone.contains(gt))
                {
                    // thing no longer listening to this tile.
                    List<Locatable> listeningHere = whosListening.get(gt);

                    if(listeningHere == null || !listeningHere.remove(thing))
                        throw new RuntimeException("Tried to unregister thing from listening to tile, wasn't registred there.");

                    // Let thing know that objects on this tile have left its
                    // influence radius
                    List<Locatable> stuffHere = whosThere.get(gt);

                    if(stuffHere == null)
                    {
                        stuffHere = new ArrayList<Locatable>();
                        whosThere.put(gt, stuffHere);
                    }

                    for(Locatable l : stuffHere)
                    {
                        if(l != thing)
                            thing.exited(l);
                    }
                }
            }
        }
    }

    /**
     * Registers a new object at a certain game tile.
     *
     * @param thing the new thing being registered.
     */
    public void register(Locatable thing)
    {
        GameTile location = thing.location();

        // Add the thing to the location.
        List<Locatable> atLoc = whosThere.get(location);

        if(atLoc == null)
        {
            atLoc = new ArrayList<Locatable>();
            whosThere.put(location, atLoc);
        }

        atLoc.add(thing);

        // Notify objects listening at this location that the thing has entered
        // their influence radii.
        List<Locatable> listeningThere = whosListening.get(location);

        if(listeningThere == null)
        {
            listeningThere = new ArrayList<Locatable>();
            whosListening.put(location, listeningThere);
        }

        for(Locatable l : listeningThere)
        {
            l.entered(thing);
        }

        // Notify thing of objects within its influence radius and register it
        // to listen at these locations.
        List<GameTile> thingRadius = MapSpacialManager.getTilesAround(location, thing.influenceRadius());

        for(GameTile gt : thingRadius)
        {
            List<Locatable> entering = whosThere.get(gt);

            for(Locatable l : entering)
            {
                if(l != thing)
                    thing.entered(l);
            }

            List<Locatable> listening = whosListening.get(gt);

            if(listening == null)
            {
                listening = new ArrayList<Locatable>();
                whosListening.put(gt, listening);
            }

            listening.add(thing);
        }

        thing.addMovementListener(this);
        thing.addRadiusListener(this);
    }

    /**
     * Unregisters the thing from receiving updates about movements and tells
     * objects which had an influence radius that included the thing that it
     * has exited their influence radius.
     *
     * @param thing the locatable thing being unregistered from the location manager.
     */
    public void unregister(Locatable thing)
    {
        GameTile location = thing.location();

        // Remove thing from its location.
        List<Locatable> atLoc = whosThere.get(location);

        if(atLoc == null || !atLoc.remove(thing))
            throw new RuntimeException("Attempting to unregister thing, not registered to its location.");

        // Let things that were listening at that location know that thing has
        // left their influence radii
        List<Locatable> listeningAtLoc = whosListening.get(location);

        for(Locatable l : listeningAtLoc)
        {
            if(l != thing)
                l.exited(thing);
        }

        // Unregister thing from receiving movement updates in its radius.
        List<GameTile> thingRadius = MapSpacialManager.getTilesAround(location, thing.influenceRadius());

        for(GameTile gt : thingRadius)
        {
            List<Locatable> listeningHere = whosListening.get(gt);
            if(!listeningHere.remove(thing))
                throw new RuntimeException("Attempted to unregister thing from listening to a tile it was registered at.");

            List<Locatable> exiting = whosThere.get(gt);

            for(Locatable l : exiting)
            {
                if(l != thing)
                    thing.exited(l);
            }
        }

        thing.removeMovementListener(this);
        thing.removeRadiusListener(this);
    }
}
