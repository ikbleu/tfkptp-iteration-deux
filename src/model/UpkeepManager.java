/*
 * file: UpkeepManager.java
 */

package src.model;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

import src.model.instances.Instance;
import src.model.interfaces.Tickable;

/**
 * Extends ResourceManager to allow for instances to register to receive upkeep.
 * Registered instances may receive up to a certain percentage of the total
 * resources available or their requested upkeep, whichever is lower.
 *
 * @author Christopher Dudley
 */
public class UpkeepManager implements Tickable
{
    // Percentage distribution of resources to instances.
    private HashMap<Instance, Double> rscDistribution;

    // List of instances registered with the upkeep manager.
    private ArrayList<Instance> registered;

    // Resource manager to check for resources from.
    private ResourceManager rscMan;

    /**
     * Creates a new upkeep manager with resource manager to check resources
     * with and remove resources from.
     */
    public UpkeepManager(ResourceManager rm)
    {
        rscMan = rm;
        rscDistribution = new HashMap<Instance, Double>();
        registered = new ArrayList<Instance>();
    }

    /**
     * Registers an instance to have its upkeep managed.
     *
     * @param i the instance to manage.
     */
    public void register(Instance i)
    {
        if(!registered.contains(i))
            registered.add(i);

        rscDistribution.put(i, 0d);
    }

    /**
     * Unregisters an instance that was being managed.
     *
     * @param i the instance to stop managing.
     */
    public void unregister(Instance i)
    {
        registered.remove(i);
        rscDistribution.remove(i);
    }

    /**
     * Sets the specified instance to have a ratio of total resources that will
     * be allocated to it if there are not enough resources for everything.
     *
     * @param i the instance to set rationing for.
     * @param ratio the ratio at which to ration resources.
     */
    public void setRation(Instance i, double ratio)
    {
        rscDistribution.put(i, ratio);
    }

    /**
     * Determines the total amount of upkeep required and, if less than current
     * resource totals, simply dispenses the correct about of resources to each
     * registered instance.
     *
     * If the amount of resources required for upkeep is greater than the total
     * amount of any resource owned by the player, then  for those resources
     * will be subject to distribution percentages with resources being
     * rationed to certain units at certain percentages. Instances will be
     * given either however much upkeep they ask for or how much they may
     * have rationed to them, whichever is smaller.
     */
    public void tick()
    {
        HashMap<String, Integer> upkeepTotal = new HashMap<String, Integer>();
        upkeepTotal.put("rscFood", 0);
        upkeepTotal.put("rscMetal", 0);
        upkeepTotal.put("rscEnergy", 0);

        for(Instance i : registered)
        {
            Map<String, Integer> instUpkeep = i.getUpkeep();

            for(String type : upkeepTotal.keySet())
            {
                int curVal = upkeepTotal.get(type);
                int unitVal = instUpkeep.get(type);

                upkeepTotal.put(type, curVal + unitVal);
            }
        }

        Map<String, Integer> playerTotal = rscMan.getAllAmounts();
        HashMap<String, Boolean> ration = new HashMap<String, Boolean>();

        for(String type : upkeepTotal.keySet())
        {
            if(upkeepTotal.get(type) > playerTotal.get(type))
                ration.put(type, true);
            else
                ration.put(type, false);
        }

        for(Instance i : registered)
        {
            HashMap<String, Integer> rscToSend = new HashMap<String, Integer>();
            Map<String, Integer> rscDesired = i.getUpkeep();

            Map<String, Integer> curPlayTotal = rscMan.getAllAmounts();

            for(String type : upkeepTotal.keySet())
            {
                int amtDesired = rscDesired.get(type);

                if(ration.get(type))
                {
                    int canHave = (int) (rscDistribution.get(i) * playerTotal.get(type));
                    int toSend = Math.min(amtDesired, canHave);
                    toSend = Math.max(toSend, 0);
                    toSend = Math.min(toSend, curPlayTotal.get(type));
                    
                    rscToSend.put(type, toSend);
                }
                else
                {
                    rscToSend.put(type, amtDesired);
                }
            }

            if(!rscMan.spend(rscToSend))
                throw new RuntimeException("There weren't enough resources?!");
            i.sentUpkeep(rscToSend);
        }
    }
}
