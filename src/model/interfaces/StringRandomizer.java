package src.model.interfaces;

import java.util.Set;

public interface StringRandomizer {
	public String random();
	public String defaultValue();
	public Set<String> possibleValues();
}
