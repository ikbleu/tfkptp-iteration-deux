package src.model.instances;

import java.util.Map;

import src.model.Player;
import src.model.interfaces.GameTile;
import src.model.interfaces.HasPlayerVisitor;
import src.model.interfaces.vInstanceVisitor;
import src.model.interfaces.vStructure;
import src.model.interfaces.InstanceVisitor;

public abstract class Structure extends Instance implements vStructure {
	public Structure( Player p, int id, GameTile g )
	{
		super( p, id, g );
	}
	
	final public void accept( InstanceVisitor iv )
	{
		iv.visitStructure( this );
	}
	
	final public void accept( vInstanceVisitor iv )
	{
		iv.visitStructure( this );
	}
	
	public void accept( HasPlayerVisitor v )
	{
		v.visitStructure( this );
	}
	
	@Override
	public Map<String, Integer> getUpkeep() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sentUpkeep(Map<String, Integer> resources) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int workers() {
		// TODO Auto-generated method stub
		return 0;
	}
}
