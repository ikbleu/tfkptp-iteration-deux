package src.model.instances;

import java.util.ArrayList;
import java.util.List;

import src.model.Player;
import src.model.commands.CommandFactory;
import src.model.interfaces.GameTile;
import src.model.interfaces.HasPlayerVisitor;
import src.model.interfaces.vUnit;
import src.model.interfaces.InstanceVisitor;

public abstract class Unit extends Instance implements vUnit {
	public Unit( Player p, int id, GameTile g )
	{
		super( p, id, g );
	}
	
	final public void accept( InstanceVisitor iv )
	{
		iv.visitUnit( this );
	}
	
	public void powerUp()
	{
		
	}
	
	public void powerDown()
	{
		
	}
	
	public void decommission()
	{
		destroy();
	}
	
	public void addToRallyPoint( RallyPoint rp )
	{
		rp.addUnit( this );
	}
	
	public void accept( HasPlayerVisitor v )
	{
		v.visitUnit( this );
	}
	
	public void addRallyPointCommand( CommandFactory cf )
	{
		rallyCommands.add( cf );
	}
	private List< CommandFactory > rallyCommands = new ArrayList< CommandFactory >();
	
	public void rallyCommands( List< CommandFactory > l )
	{
		l.addAll( rallyCommands );
	}
}
