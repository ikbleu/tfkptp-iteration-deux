package src.model.instances.units;

import src.model.instances.Instance;
import src.model.instances.InstanceExistenceListener;

public class GeneralUnitManager implements InstanceExistenceListener
{
	private static final int MAX_UNITS = 25;
	
	private int numUnits = 0;
	public boolean canMakeUnit()
	{
		return ( numUnits < MAX_UNITS );
	}
	
	public void delInstance(Instance i) {
		--numUnits;
		System.out.println( "we now have " + numUnits + " units" );
	}
	
	public void newInstance(Instance i) {
		++numUnits;
		System.out.println( "we now have " + numUnits + " units" );
	}
}
