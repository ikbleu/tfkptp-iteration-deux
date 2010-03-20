package src.model.instances;

import java.util.List;

import src.model.interfaces.GameTile;
import src.model.interfaces.vRallyPoint;
import src.model.interfaces.InstanceVisitor;
import src.model.interfaces.vUnit;

public class RallyPoint extends Instance implements vRallyPoint {
	public RallyPoint( GameTile g )
	{
		super( g );
	}
	
	final public void accept( InstanceVisitor iv )
	{
		iv.visitRallyPoint( this );
	}
	
	public void instanceEntered(Instance i) {
		// attack it?
	}
	
	public void instanceExited(Instance i) {
		// stop attacking it?
	}
	
	public String token()
	{
		return "rallyPoint";
	}

	@Override
	public int id() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<vUnit> units() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int workers() {
		// TODO Auto-generated method stub
		return 0;
	}
}
