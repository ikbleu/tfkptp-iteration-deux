package src.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import src.model.enums.Direction;
import src.model.enums.Visibility;
import src.model.instances.RallyPoint;
import src.model.instances.Structure;
import src.model.instances.Unit;
import src.model.instances.WorkerGroup;
import src.model.interfaces.GameTile;
import src.model.interfaces.HasPlayer;
import src.model.interfaces.HasPlayerVisitor;

public class VisibilityMap implements HasPlayerVisitor
{
	private Map<GameTile, Integer> unitsPerTile;
	private Map<GameTile, String> structureOnTile;
	private Map<GameTile, Set<RPPointingID> > rallyPointsOnTile;
	private Map<GameTile, Set<WorkerID> > workersOnTile;
	private Map<GameTile, Map<String, Integer> > resourcesOnTile;
	private Set<GameTile> exploredTiles;
	private Map<GameTile, Visibility> seenTiles;
	private Map<GameTile, String> playerOwningTile;
	
	private Map<GameTile, Integer> unitAffectedTiles;
	private Map<GameTile, RPPointingID> rallyPointAffectedTiles;
	private Map<GameTile, String> structureAffectedTiles;
	private Map<GameTile, String> playerAffectedTiles;
	private Map<GameTile, WorkerID> workerAffectedTiles;

	public VisibilityMap()
	{
		unitsPerTile = new HashMap<GameTile, Integer>();
		structureOnTile = new HashMap<GameTile, String>();
		rallyPointsOnTile = new HashMap<GameTile, Set<RPPointingID> >();
		workersOnTile = new HashMap<GameTile, Set<WorkerID> >();
		resourcesOnTile = new HashMap<GameTile, Map<String, Integer> >();
		exploredTiles = new HashSet<GameTile>();
		playerOwningTile = new HashMap<GameTile, String>();
	}
	
	public void updateVisibility(Set<GameTile> newVisibles, Set<HasPlayer> hasPlayers)
	{
		Set<GameTile> forgotten = seenTiles.keySet();
		Iterator<GameTile> i = forgotten.iterator();
		
		while (i.hasNext())
		{
			seenTiles.put(i.next(), Visibility.NON_VISIBLE);
		}
		
		i = newVisibles.iterator();
		while (i.hasNext())
		{
			seenTiles.put(i.next(), Visibility.VISIBLE);
		}
		
		Iterator<HasPlayer> i2 = hasPlayers.iterator();
		
		while (i2.hasNext())
		{
			i2.next().accept(this);
		}
	}
	
	@Override
	public void visitRallyPoint(RallyPoint rp)
	{
		// TODO Auto-generated method stub
		GameTile loc = rp.location();
		
	}

	@Override
	public void visitStructure(Structure s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitUnit(Unit u) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitWorkerGroup(WorkerGroup wg) {
		// TODO Auto-generated method stub
		
	}
	
	private class RPPointingID
	{
		private int rallyID;
		private Direction facingDir;
		private String action;
		
		public RPPointingID(RallyPoint rp)
		{
			rallyID = rp.id();
			facingDir = rp.getFacingDirection();
			action = rp.getCurrentAction();
		}
		
		public int getRallyID()
		{
			return rallyID;
		}
		
		public Direction getFacingDirection()
		{
			return facingDir;
		}
		
		public String getAction()
		{
			return action;
		}
	}
	
	private class WorkerID
	{
		private int numWorkers;
		private String type;
		
		public WorkerID(WorkerGroup wg)
		{
			numWorkers = wg.numWorkers();
			type = wg.token();
		}
		
		public int numWorkers()
		{
			return numWorkers;
		}
		
		public String groupType()
		{
			return type;
		}
	}

	public void explore(List<GameTile> tileList)
	{
		exploredTiles.addAll(tileList);
		
	}
}
