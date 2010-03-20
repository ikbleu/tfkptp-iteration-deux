package src.model.instances.units;

import src.model.Player;
import src.model.instances.GeneralUnitManager;
import src.model.instances.Instance;
import src.model.instances.InstanceExistenceListener;

public abstract class SpecificUnitManager implements InstanceExistenceListener {
	public SpecificUnitManager(GeneralUnitManager m) {
		manager = m;
	}
	private GeneralUnitManager manager; 
	
	protected boolean canMakeUnit()
	{
		return canMakeSpecificUnit() && manager.canMakeUnit();
	}
	
	abstract protected boolean canMakeSpecificUnit();
	
	public void delInstance(Instance i) {
		manager.delInstance( i );
		doDelInstance( i );
	}
	abstract protected void doDelInstance( Instance i );
	
	public void newInstance(Instance i) {
		manager.newInstance( i );
		doNewInstance( i );
	}
	abstract protected void doNewInstance( Instance i );
}
