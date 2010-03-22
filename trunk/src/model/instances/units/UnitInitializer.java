package src.model.instances.units;

import src.model.Player;
import src.model.control.Device;
import src.model.control.KeyEventInterpreterBuilder;
import src.model.instances.GeneralUnitManager;
import src.model.interfaces.ViewVisitor;
import src.model.interfaces.vGroup;
import src.util.Hand;

public final class UnitInitializer {
	public static void initialize( final Player p )
	{
		GeneralUnitManager m = new GeneralUnitManager();
		final Hand< Device > hand = p.handFactory().make( Device.class );
		Device d = new vGroup()
		{
			public String context()
			{
				return "Group";
			}
			
			public String meaning()
			{
				return "groupUnit";
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
			
			public void accept( ViewVisitor v )
			{
				v.visitGroup( this );
			}

			@Override
			public String token() {
				// TODO Auto-generated method stub
				return meaning();
			}
		};
		
		hand.add( new RangedManager( p, m ) );
		// TODO: other managers
		
		p.addDevice( d );
	}
}
