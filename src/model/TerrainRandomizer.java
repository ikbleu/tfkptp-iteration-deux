package src.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import src.model.interfaces.StringRandomizer;

public class TerrainRandomizer implements StringRandomizer
{
	private Map<String, Integer> map;
	
	public TerrainRandomizer()
	{
		map = new HashMap<String, Integer>();
		
		map.put("GRASSLAND", new Integer(50));
		map.put("SPARSE_FOREST", new Integer(30));
		map.put("WATER", new Integer(15));
		map.put("OUTER_SPACE", new Integer(5));
	}
	
	@Override
	public String random()
	{
		int i = (int)(Math.random()*100);
		
		int tot = map.get("GRASSLAND").intValue();
		
		if (i < tot)
			return "GRASSLAND";
		
		tot += map.get("SPARSE_FOREST").intValue();
		
		if (i < tot)
			return "SPARSE_FOREST";
		
		tot += map.get("WATER").intValue();
		
		if (i < tot)
			return "WATER";
		
		return "OUTER_SPACE";
	}
	
	public String defaultValue()
	{
		return "GRASSLAND";
	}
	
	public Set<String> possibleValues()
	{
		return map.keySet();
	}

}
