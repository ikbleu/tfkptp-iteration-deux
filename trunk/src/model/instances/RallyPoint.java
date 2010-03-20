package src.model.instances;

import java.util.List;

import src.model.Player;
import src.model.interfaces.GameTile;
import src.model.interfaces.vRallyPoint;
import src.model.interfaces.InstanceVisitor;
import src.model.interfaces.vUnit;

public class RallyPoint extends Instance implements vRallyPoint {
	public RallyPoint( Player p, int id, GameTile g )
	{
		super( p, id, g );
	}
	
	final public void accept( InstanceVisitor iv )
	{
		iv.visitRallyPoint( this );
	}
	
	public void entered(Locatable l) {
		// attack it?
	}
	
	public void exited(Locatable l) {
		// stop attacking it?
	}
	
	public String token()
	{
		return "instanceRallyPoint";
	}

	@Override
	public void units( List< vUnit > l ) {
		// TODO Auto-generated method stub
	}

	@Override
	public int workers() {
		// TODO Auto-generated method stub
		return 0;
	}
}
