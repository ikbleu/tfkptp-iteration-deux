package src.model.interfaces;

import java.util.Map;

public interface vStructure {
	Map< String, Integer > stats();
	public int id();
	public int health();
	public String token();
	public int workers();
}
