package src.model.interfaces;

import java.util.List;
import java.util.Map;

import src.model.commands.CommandFactory;

public interface vUnit extends vInstance
{
	void stats( Map< String, Integer > m );
	public int id();
	public int health();
	public String token();
	public void rallyCommands( List< CommandFactory > l );
	public GameTile location();
	boolean isInRallyPoint();
}
