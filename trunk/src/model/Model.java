package src.model;

import src.model.instances.structures.StructureInitializer;
import src.model.instances.units.UnitInitializer;

public class Model {
	public Model()
	{
		UnitInitializer.initialize();
		StructureInitializer.initialize();
	}
}
