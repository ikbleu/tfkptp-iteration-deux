/*
 * file: ResourceManager
 */

package src.model;

import src.model.commands.CommandListener;
import src.model.commands.Command;

import java.util.Map;
import java.util.HashMap;
import java.util.Collections;

/**
 * Manage's a player's current resources and things that can be done with those
 * resources.
 *
 * @author Christopher Dudley
 */
public class ResourceManager
{
    // The rates at which workers convert raw resources to usable resources.
    private static final double BASE_ORE_CONV = 5.0;
    private static final double BASE_GRAIN_CONV = 5.0;
    private static final double BASE_FUEL_CONV = 5.0;

    private static final double DELTA_ORE_CONV = 0.5;
    private static final double DELTA_GRAIN_CONV = 0.5;
    private static final double DELTA_FUEL_CONV = 0.5;

    private Map<String, Integer> resources;
    private Map<String, String> conversions;
    private Map<String, Double> conversionRates;

    private Player owner;

    public ResourceManager(Player p, Map<String, Integer> startResources)
    {
        for(String type : startResources.keySet())
            this.resources.put(type, startResources.get(type));

        conversions = new HashMap<String, String>();
        conversions.put("rscOre", "rscMetal");
        conversions.put("rscGrain", "rscFood");
        conversions.put("rscFuel", "rscEnergy");

        conversionRates = new HashMap<String, Double>();
        conversionRates.put("rscOre", BASE_ORE_CONV);
        conversionRates.put("rscGrain", BASE_GRAIN_CONV);
        conversionRates.put("rscFuel", BASE_FUEL_CONV);

        owner = p;

        initCommListeners();
    }

    private void initCommListeners()
    {
        owner.addCommandListener("cmdResWorkerMetal", new CommandListener() {
            public void commandOccurred(Command com)
            {
                if(com.when().equals("execute"))
                {
                    double prevRate = conversionRates.get("rscOre");
                    conversionRates.put("rscOre", prevRate + DELTA_ORE_CONV);
                }
            }
        });

        owner.addCommandListener("cmdResWorkerFood", new CommandListener() {
            public void commandOccurred(Command com)
            {
                if(com.when().equals("execute"))
                {
                    double prevRate = conversionRates.get("rscGrain");
                    conversionRates.put("rscGrain", prevRate + DELTA_GRAIN_CONV);
                }
            }
        });

        owner.addCommandListener("cmdResWorkerEnergy", new CommandListener() {
            public void commandOccurred(Command com)
            {
                if(com.when().equals("execute"))
                {
                    double prevRate = conversionRates.get("rscFuel");
                    conversionRates.put("rscFuel", prevRate + DELTA_FUEL_CONV);
                }
            }
        });
    }

    /**
     * Attempts to spend the specified amount of resources. If there are enough
     * resources to spend for each type, the resources will be subtracted and
     * true returned. A false will be returned otherwise and no resources will
     * be spent.
     *
     * @param toSpend a map of resource type to the amount to spend of it.
     *
     * @return whether or not the resources were spent.
     */
    public boolean spend(Map<String, Integer> toSpend)
    {
        boolean canSpend = true;

        for(String type : toSpend.keySet())
        {
            if(!resources.containsKey(type))
                throw new RuntimeException("Asked to spend invalid resource type");

            if(resources.get(type) < toSpend.get(type))
                canSpend = false;
        }

        if(canSpend)
        {
            for(String type : toSpend.keySet())
            {
                int curVal = resources.get(type);
                curVal -= toSpend.get(type);
                resources.put(type, curVal);
            }
        }

        return canSpend;
    }

    /**
     * Converts the specified raw resource type to its usable resource type
     * at a rate modified by the number of workers working on the conversion.
     *
     * @param type the type of raw resource to convert.
     * @param numWorkers the number of workers converting the resource.
     */
    public void convert(String type, int numWorkers)
    {
        if(!conversions.containsKey(type))
            throw new RuntimeException("Asked to convert non-raw type.");

        double convRate = conversionRates.get(type);

        int maxToConvert = (int) (numWorkers * convRate);

        int curRaw = resources.get(type);

        int amtToConvert = Math.min(maxToConvert, curRaw);

        resources.put(type, curRaw - amtToConvert);

        int curUsable = resources.get(conversions.get(type));

        resources.put(conversions.get(type), curUsable + amtToConvert);
    }

    /**
     * Returns the amount of the specified resource that the player has in
     * their possession.
     *
     * @param type the type of resource.
     * @return the amount of the specified type of resource the player has.
     */
    public int getAmount(String type)
    {
        return resources.get(type);
    }

    /**
     * Returns an unmodifiable map from the types of resources the player has
     * to the amounts of those resources.
     *
     * @return the player's current resources.
     */
    public Map<String, Integer> getAllAmounts()
    {
        return Collections.unmodifiableMap(resources);
    }

    public void addAmmount(String type, int amount)
    {
        if(!resources.containsKey(type))
            throw new RuntimeException("Attempting to add invalid resource type.");

        int curVal = resources.get(type);
        resources.put(type, curVal + amount);
    }
}
