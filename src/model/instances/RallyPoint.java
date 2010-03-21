package src.model.instances;

import java.util.List;

import src.model.Player;
import src.model.enums.Direction;
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
	
	public void entered(Instance l) {
		// attack it?
	}
	
	public void exited(Instance l) {
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
	
	public Direction getFacingDirection()
	{
		throw new RuntimeException("TODO");
	}
	
	public String getCurrentAction()
	{
		throw new RuntimeException("TODO");
	}
}
