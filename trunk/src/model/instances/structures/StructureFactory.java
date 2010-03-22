package src.model.instances.structures;

import src.model.instances.InstanceExistenceListener;
import src.model.instances.InstanceFactory;
import src.model.instances.Structure;
import src.model.interfaces.GameTile;

abstract class StructureFactory implements InstanceFactory, InstanceExistenceListener {
	abstract public Structure makeInstance( GameTile g );
}
