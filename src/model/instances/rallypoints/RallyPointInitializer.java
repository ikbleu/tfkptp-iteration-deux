package src.model.instances.rallypoints;

import src.model.Player;
import src.model.control.Device;
import src.model.control.KeyEventInterpreterBuilder;
import src.model.instances.units.RangedManager;
import src.util.Hand;

public class RallyPointInitializer {
	public static void initialize( final Player p )
	{
		final Hand< Device > rpHand = p.handFactory().make( Device.class );
		final Hand< Device > armyHand = p.handFactory().make( Device.class );
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
				builder.devices( rpHand.spawnLens() );
			}

			@Override
			public String comparable() {
				// TODO Auto-generated method stub
				return meaning();
			}
		};
		Device d2 = new Device()
		{
			public String context()
			{
				return "Group";
			}
			
			public String meaning()
			{
				return "Army";
			}
			
			public void direct(KeyEventInterpreterBuilder builder)
			{
				builder.devices( armyHand.spawnLens() );
			}

			@Override
			public String comparable() {
				// TODO Auto-generated method stub
				return meaning();
			}
		};
		
		new RallyPointManager( p, rpHand, armyHand );
		
		p.addDevice( d );
		p.addDevice( d2 );
	}
}
