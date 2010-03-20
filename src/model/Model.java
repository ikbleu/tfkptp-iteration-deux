package src.model;

import src.model.instances.structures.StructureInitializer;
import src.model.instances.units.UnitInitializer;

public class Model {
	public Player p; // TODO: remove (testing purposes)
	public Model()
	{
		p = new Player();
		// TODO: ai?
		
		UnitInitializer.initialize( p );
		StructureInitializer.initialize( p );
	}
}
