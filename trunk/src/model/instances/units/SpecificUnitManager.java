package src.model.instances.units;

import src.model.instances.GeneralUnitManager;
import src.model.instances.Instance;
import src.model.instances.InstanceExistenceListener;
import src.model.instances.Unit;
import src.model.interfaces.GameTile;

public abstract class SpecificUnitManager implements InstanceExistenceListener {
	public SpecificUnitManager(GeneralUnitManager m, UnitFactory f) {
		manager = m;
		factory = f;
	}
	private GeneralUnitManager manager; 
	private UnitFactory factory;
	
	public boolean canMakeUnit()
	{
		return canMakeSpecificUnit() && manager.canMakeUnit();
	}
	
	// precondition: canMakeUnit() is true
	final public Unit makeUnit( GameTile g )
	{
		Unit u = factory.makeInstance( g );
		u.addInstanceExistenceListener( this );
		return u;
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
