package src.model.instances;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import src.model.Player;
import src.model.commands.CommandFactory;
import src.model.interfaces.GameTile;
import src.model.interfaces.HasPlayerVisitor;
import src.model.interfaces.vInstanceVisitor;
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
	
	final public void accept( vInstanceVisitor iv )
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
	
	private RallyPoint rallyPoint = null;
	public void addToRallyPoint( RallyPoint rp )
	{
		rp.addUnit( this );
		rallyPoint = rp;
	}
	
	public void removeFromRallyPoint()
	{
		rallyPoint.removeUnit( this );
		rallyPoint = null;
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
	
	public boolean isInRallyPoint()
	{
		return rallyPoint != null;
	}

    public Map<String, Integer> getUpkeep()
    {
        Map<String, Integer> upkeep = new HashMap<String, Integer>();
        upkeep.put("rscFood", getStat("statUpFood"));
        upkeep.put("rscMetal", getStat("statUpMetal"));
        upkeep.put("rscEnergy", getStat("statUpEnergy"));

        return upkeep;
    }

    public void sentUpkeep(Map<String, Integer> resources)
    {
        
    }

    public void receiveWorkers(WorkerGroup wg, int numWorkers) {}

    public void sendWorkers(WorkerGroup wg, int numWorkers) {}
    
    public boolean isInBattleGroup()
    {
    	return isInRallyPoint() && rallyPoint.isInBattleGroup( this );
    }
}
