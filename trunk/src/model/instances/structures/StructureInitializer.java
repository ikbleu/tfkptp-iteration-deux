package src.model.instances.structures;

import src.model.Player;
import src.model.control.Device;
import src.model.control.KeyEventInterpreterBuilder;
import src.model.instances.GeneralUnitManager;
import src.model.instances.units.RangedManager;
import src.util.Hand;
import src.util.HandFactory;

public final class StructureInitializer {
	public static void initialize( final Player p )
	{
		GeneralStructureManager m = new GeneralStructureManager( p );
		final Hand< Device > hand = p.handFactory().make( Device.class );
		Device d = new Device()
		{
			public String context()
			{
				return "Group";
			}
			
			public String meaning()
			{
				return "Structure";
			}
			
			public void direct(KeyEventInterpreterBuilder builder)
			{
				builder.devices( hand.spawnLens() );
			}

			@Override
			public String comparable() {
				// TODO Auto-generated method stub
				return meaning();
			}
		};
		
		// TODO: other managers
		
		p.addDevice( d );
	}
}
