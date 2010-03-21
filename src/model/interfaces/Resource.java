package src.model.interfaces;

import java.util.Map;

public interface Resource {
	public void harvest(Map<Object, Integer> m);
	public String getResourceType();
}
