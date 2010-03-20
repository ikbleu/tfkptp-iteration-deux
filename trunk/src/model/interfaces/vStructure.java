package src.model.interfaces;

import java.util.Map;

public interface vStructure {
	void stats( Map< String, Integer > m );
	public int id();
	public int health();
	public String token();
	public int workers();
}
