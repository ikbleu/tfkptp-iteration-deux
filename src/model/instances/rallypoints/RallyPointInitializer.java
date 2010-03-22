package src.model.instances.rallypoints;

import src.model.Player;
import src.model.WorkerManager;
import src.model.control.Device;
import src.model.control.KeyEventInterpreterBuilder;
import src.model.instances.units.RangedManager;
import src.model.interfaces.ViewVisitor;
import src.model.interfaces.vGroup;
import src.util.Hand;

public class RallyPointInitializer {
	public static void initialize( final Player p, WorkerManager wm )
	{
		final Hand< Device > rpHand = p.handFactory().make( Device.class );
		final Hand< Device > armyHand = p.handFactory().make( Device.class );
		Device d = new vGroup()
		{
			public String context()
			{
				return "Group";
			}
			
			public String meaning()
			{
				return "groupRallyPoint";
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

			@Override
			public String token() {
				// TODO Auto-generated method stub
				return meaning();
			}
			
			@Override
			public void accept(ViewVisitor v) {
				// TODO Auto-generated method stub
				
			}
		};
		Device d2 = new vGroup()
		{
			public String context()
			{
				return "Group";
			}
			
			public String meaning()
			{
				return "groupArmy";
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

			@Override
			public String token() {
				// TODO Auto-generated method stub
				return meaning();
			}
			
			@Override
			public void accept(ViewVisitor v) {
				// TODO Auto-generated method stub
				
			}
		};
		
		new RallyPointManager( p, rpHand, armyHand, wm );
		
		p.addDevice( d );
		p.addDevice( d2 );
	}
}
