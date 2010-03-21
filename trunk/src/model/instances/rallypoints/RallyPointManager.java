package src.model.instances.rallypoints;

import src.model.Player;
import src.model.commands.Command;
import src.model.commands.CommandListener;
import src.model.control.Device;
import src.model.instances.Instance;
import src.model.instances.InstanceExistenceListener;
import src.model.instances.RallyPoint;
import src.model.interfaces.GameTile;
import src.util.Hand;

public class RallyPointManager implements InstanceExistenceListener {
	public RallyPointManager( final Player p, Hand< Device > hand )
	{
		factory = new RallyPointFactory( p );
		p.addCommandListener( "cmdMakeRallyPoint", new CommandListener() {
			public void commandOccurred(Command c) {
				if ( ! c.when().equals( "execute" ) ) return;
				if ( canMakeRallyPoint() )
					makeRallyPoint( p.startingLocation() );
			}
		});
	}
	private RallyPointFactory factory;
	
	public boolean canMakeRallyPoint()
	{
		return true;
	}
	
	public void makeRallyPoint( GameTile g )
	{
		RallyPoint r = factory.makeRallyPoint( g );
		
		// TODO: rally points need command listeners and selectable commands
		
		r.addInstanceExistenceListener( this );
		r.addInstanceExistenceListener( factory );
	}

	@Override
	public void delInstance(Instance i) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void newInstance(Instance i) {
		// TODO Auto-generated method stub
		
	}
}
