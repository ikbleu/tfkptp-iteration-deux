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
    private static final double BASE_ORE_CONV = 1.0;
    private static final double BASE_GRAIN_CONV = 1.0;
    private static final double BASE_FUEL_CONV = 1.0;

    private static final double DELTA_ORE_CONV = 0.1;
    private static final double DELTA_GRAIN_CONV = 0.1;
    private static final double DELTA_FUEL_CONV = 0.1;

    private Map<String, Integer> rawResources;
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

        rawResources = new HashMap<String, Integer>();
        rawResources.put("rscOre", 0);
        rawResources.put("rscGrain", 0);
        rawResources.put("rscFuel", 0);

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
     * Checks to see if the specified amounts of resources to spend are within
     * the amounts of resources the player has.
     *
     * @param toSpend the amounts of each resource to spend.
     *
     * @return whether or not there are enough resources to spend that much.
     */
    public boolean canSpend(Map<String, Integer> toSpend)
    {
        boolean canSpend = true;

        for(String type : toSpend.keySet())
        {
            if(!resources.containsKey(type))
                throw new RuntimeException("Asked to spend invalid resource type");

            if(resources.get(type) < toSpend.get(type))
                canSpend = false;
        }

        return canSpend;
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
        boolean canSpend = canSpend(toSpend);

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

    public void harvested(String type, int amountHarvested, int numWorkers)
    {
        int curVal = rawResources.get(type);
        rawResources.put(type, curVal + amountHarvested);

        convert(type);
    }

    /**
     * Converts the specified raw resource type to its usable resource type
     * at a rate modified by research upgrades.
     *
     * @param type the type of raw resource to convert.
     */
    private void convert(String type)
    {
        if(!conversions.containsKey(type))
            throw new RuntimeException("Asked to convert non-raw type.");

        double convRate = conversionRates.get(type);

        int curRaw = rawResources.get(type);

        int amtToConvert = (int) (curRaw * convRate);

        rawResources.put(type, 0);

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
