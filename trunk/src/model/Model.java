package src.model;

import src.model.instances.rallypoints.RallyPointInitializer;
import src.model.instances.structures.StructureInitializer;
import src.model.instances.units.UnitInitializer;
import src.model.interfaces.Clock;
import src.util.HandFactory;

public class Model {
	public Player p; // TODO: remove (testing purposes)
	public Clock c;
	
	public Model( HandFactory hFact )
	{
		human = new Player( true, hFact, null, null, null );
		p = human; // TODO: remove
		// TODO: ai? map?
		
		UnitInitializer.initialize( human );
		StructureInitializer.initialize( human );
		RallyPointInitializer.initialize( human );
	}
	
	public Clock getClock()
	{
		return c;
	}
	
	public Player human()
	{
		return human;
	}
	private Player human;
}