package src.model.instances.units;

import src.model.Player;
import src.model.control.Device;
import src.model.control.KeyEventInterpreterBuilder;
import src.model.instances.GeneralUnitManager;
import src.util.Hand;

public final class UnitInitializer {
	public static void initialize( final Player p )
	{
		GeneralUnitManager m = new GeneralUnitManager();
		final Hand< Device > hand = p.handFactory().make( Device.class );
		Device d = new Device()
		{
			public String context()
			{
				return "Group";
			}
			
			public String meaning()
			{
				return "Unit";
			}
			
			public void direct(KeyEventInterpreterBuilder builder)
			{
				builder.devices( hand.spawnLens() );
			}
		};
		
		hand.add( new RangedManager( p, m ) );
		// TODO: other managers
		
		p.addDevice( d );
	}
}
