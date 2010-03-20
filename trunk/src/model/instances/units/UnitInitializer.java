package src.model.instances.units;

import src.model.Player;
import src.model.instances.GeneralUnitManager;

public final class UnitInitializer {
	public static void initialize( Player p )
	{
		GeneralUnitManager m = new GeneralUnitManager();
		new RangedManager( p, m );
	}
}
