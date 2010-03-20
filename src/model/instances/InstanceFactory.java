package src.model.instances;

import src.model.interfaces.GameTile;

public interface InstanceFactory {
	public Instance makeInstance( GameTile g );
}
