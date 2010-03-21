package src.model.instances;

import src.model.interfaces.GameTile;

public interface InstanceFactory extends InstanceExistenceListener {
	public Instance makeInstance( GameTile g );
}
