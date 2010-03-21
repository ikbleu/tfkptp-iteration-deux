package src.model.instances.rallypoints;

import src.model.Player;
import src.model.control.Device;
import src.model.control.KeyEventInterpreterBuilder;
import src.model.instances.units.RangedManager;
import src.util.Hand;

public class RallyPointInitializer {
	public static void initialize( final Player p )
	{
		final Hand< Device > hand = p.handFactory().make( Device.class );
		Device d = new Device()
		{
			public String context()
			{
				return "Group";
			}
			
			public String meaning()
			{
				return "Rally Point";
			}
			
			public void direct(KeyEventInterpreterBuilder builder)
			{
				builder.devices( hand.spawnLens() );
			}
		};
		
		new RallyPointManager( p, hand );
		
		p.addDevice( d );
	}
}
