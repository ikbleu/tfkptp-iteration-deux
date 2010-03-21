package src.model;

import src.model.instances.structures.StructureInitializer;
import src.model.instances.units.UnitInitializer;
import src.util.HandFactory;

public class Model {
	public Player p; // TODO: remove (testing purposes)
	public Model( HandFactory hFact )
	{
		p = new Player( true );
		// TODO: ai?
		
		UnitInitializer.initialize( p );
		StructureInitializer.initialize( p );
	}
}
