package src.model.instances.units;

import src.model.instances.InstanceFactory;
import src.model.instances.Unit;

interface UnitFactory extends InstanceFactory {
	public Unit makeInstance();
}
