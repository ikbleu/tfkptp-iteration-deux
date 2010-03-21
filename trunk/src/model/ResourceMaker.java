package src.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import src.model.interfaces.Clock;
import src.model.interfaces.IntRandomizer;
import src.model.interfaces.Resource;

public class ResourceMaker implements IntRandomizer
{
	private Clock clock;
	private final int range = 1000;
	private Map<String, Boolean> possibleResources = potentialResources();
	
	public ResourceMaker(Clock clock)
	{
		this.clock = clock;
		
	}
	
	private Map<String, Boolean> potentialResources()
	{
		Map<String, Boolean> r = new HashMap<String, Boolean>();
		r.put("Food", new Boolean(true));
		r.put("Ore", new Boolean(false));
		r.put("Energy", new Boolean(false));
		
		return r;
	}
	
	public Set<String> possibleResourceValues()
	{
		return possibleResources.keySet();
	}
	
	public Set<Resource> getResources(boolean random)
	{
		Iterator<String> i = possibleResourceValues().iterator();
		
		Set<Resource> s = new HashSet<Resource>();
		
		while (i.hasNext())
		{
			if (random)
				s.add(getRandomAmountResource(i.next()));
			else
				s.add(getDefaultAmountResource(i.next()));
		}
		
		return s;
	}
	
	public Resource getRandomAmountResource(String s)
	{
		if (!possibleResources.containsKey(s))
			return null;
		
		Resource r;
		int amount = random();
		
		if (possibleResources.get(s).booleanValue())
			r = new RenewableResource(s, amount, clock);
		else
			r = new NonRenewableResource(s, amount);
		
		return r;
	}
	
	public Resource getDefaultAmountResource(String s)
	{
		if (!possibleResources.containsKey(s))
			return null;
		
		Resource r;
		int amount = defaultValue();
		
		if (possibleResources.get(s).booleanValue())
			r = new RenewableResource(s, amount, clock);
		else
			r = new NonRenewableResource(s, amount);
		
		return r;
	}

	@Override
	public int defaultValue() {
		// TODO Auto-generated method stub
		return range/2;
	}

	@Override
	public int possibleRange()
	{
		return range;
	}

	@Override
	public int random() {

		return (int)(Math.random()*range);
	}

}

