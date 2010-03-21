package src.model.instances.rallypoints;

import src.model.Player;
import src.model.commands.Command;
import src.model.commands.CommandListener;
import src.model.control.Device;
import src.model.instances.Instance;
import src.model.instances.InstanceExistenceListener;
import src.model.instances.RallyPoint;
import src.model.interfaces.GameTile;
import src.model.interfaces.InstanceAdapter;
import src.model.interfaces.vRallyPoint;
import src.util.Hand;

public class RallyPointManager implements InstanceExistenceListener {
	private static final int MAX_RALLY_POINTS = 10;
	public static RallyPoint lastRP = null; // TODO: remove
	
	public RallyPointManager( final Player p, Hand< Device > rpH, Hand< Device > aH )
	{
		factory = new RallyPointFactory( p );
		this.rpHand = rpH;
		this.armyHand = aH;
		p.addCommandListener( "cmdMakeRallyPoint", new CommandListener() {
			public void commandOccurred(Command c) {
				if ( ! c.when().equals( "execute" ) ) return;
				if ( canMakeRallyPoint() )
					makeRallyPoint( p.startingLocation() );
			}
		});
	}
	private RallyPointFactory factory;
	private Hand< Device > rpHand;
	private Hand< Device > armyHand;
	
	public boolean canMakeRallyPoint()
	{
		return numRallyPoints < MAX_RALLY_POINTS;
	}
	private int numRallyPoints = 0;
	
	public void makeRallyPoint( GameTile g )
	{
		RallyPoint r = factory.makeRallyPoint( g );
		lastRP = r; // TODO: remove
		
		// TODO: rally points need command listeners and selectable commands
		
		r.addInstanceExistenceListener( this );
		r.addInstanceExistenceListener( factory );
	}

	@Override
	public void delInstance(Instance i) {
		// TODO Auto-generated method stub
		--numRallyPoints;
		rpHand.remove( i );
		i.accept( new InstanceAdapter() {
			public void visitRallyPoint( vRallyPoint r )
			{
				armyHand.remove( r.armyDevice() );
			}
		});
	}

	@Override
	public void newInstance(Instance i) {
		// TODO Auto-generated method stub
		++numRallyPoints;
		rpHand.add( i );
		i.accept( new InstanceAdapter() {
			public void visitRallyPoint( vRallyPoint r )
			{
				armyHand.add( r.armyDevice() );
			}
		});
	}
}
