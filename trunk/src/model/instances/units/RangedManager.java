package src.model.instances.units;

import src.model.Player;
import src.model.instances.GeneralUnitManager;
import src.model.instances.Instance;

public class RangedManager extends SpecificUnitManager {
	private static final int MAX_RANGED = 10;
	
	public RangedManager(Player p, GeneralUnitManager m) {
		super( m, new RangedFactory( p ) );
	}
	
	protected boolean canMakeSpecificUnit()
	{
		return numRanged < MAX_RANGED;
		// TODO: check resource levels
	}
	
	private int numRanged = 0;
	protected void doDelInstance(Instance i) {
		--numRanged;
	}
	
	protected void doNewInstance(Instance i) {
		++numRanged;
		// TODO: subtract resources
	}
}
