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
	private Map<GameTile, Boolean> playerOwningTile;
	
	private Map<GameTile, Integer> unitAffectedTiles;
	private Map<GameTile, Set<RPPointingID> > rallyPointAffectedTiles;
	private Map<GameTile, String> structureAffectedTiles;
	private Map<GameTile, Boolean> playerAffectedTiles;
	private Map<GameTile, Set<WorkerID> > workerAffectedTiles;

	public VisibilityMap()
	{
		unitsPerTile = new HashMap<GameTile, Integer>();
		structureOnTile = new HashMap<GameTile, String>();
		rallyPointsOnTile = new HashMap<GameTile, Set<RPPointingID> >();
		workersOnTile = new HashMap<GameTile, Set<WorkerID> >();
		resourcesOnTile = new HashMap<GameTile, Map<String, Integer> >();
		exploredTiles = new HashSet<GameTile>();
		playerOwningTile = new HashMap<GameTile, Boolean>();
		
		unitAffectedTiles = new HashMap<GameTile, Integer>();
		structureAffectedTiles = new HashMap<GameTile, String>();
		rallyPointAffectedTiles = new HashMap<GameTile, Set<RPPointingID> >();
		workerAffectedTiles = new HashMap<GameTile, Set<WorkerID> >();
		playerAffectedTiles = new HashMap<GameTile, Boolean>();
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
		
		i = newVisibles.iterator();
		while (i.hasNext())
		{
			GameTile x = i.next();
			
			rallyPointsOnTile.remove(x);
			unitsPerTile.remove(x);
			structureOnTile.remove(x);
			workersOnTile.remove(x);
			playerOwningTile.remove(x);
		}
		
		Iterator<HasPlayer> i2 = hasPlayers.iterator();
		
		while (i2.hasNext())
		{
			i2.next().accept(this);
		}
		
		updateResources();
	}
	
	private void updateResources() {
		// TODO Auto-generated method stub
		Set<GameTile> tiles = seenTiles.keySet();
		
		Iterator<GameTile> i = tiles.iterator();
		while (i.hasNext())
		{
			GameTile loc = i.next();
			Map<String, Integer> m = resourcesOnTile.get(loc);
		}
	}

	@Override
	public void visitRallyPoint(RallyPoint rp)
	{
		GameTile loc = rp.location();
		RPPointingID rpid = new RPPointingID(rp);
		
		Set<RPPointingID> s;
		
		if (!rallyPointAffectedTiles.containsKey(loc))
		{
			s = new HashSet<RPPointingID>();
		}
		else
		{
			s = rallyPointAffectedTiles.get(loc);
		}
		s.add(rpid);
		
		rallyPointAffectedTiles.put(loc, s);
		playerAffectedTiles.put(loc, new Boolean(rp.getPlayer().isHuman()));
	}

	@Override
	public void visitStructure(Structure s)
	{
		GameTile loc = s.location();
		String sType = s.token();
		
		structureAffectedTiles.put(loc, sType);
		playerAffectedTiles.put(loc, new Boolean(s.getPlayer().isHuman()));		
	}

	@Override
	public void visitUnit(Unit u)
	{
		GameTile loc = u.location();
		
		Integer i;
		
		if (!unitAffectedTiles.containsKey(loc))
		{
			i = new Integer(1);
		}
		else
		{
			i = new Integer(unitAffectedTiles.get(loc).intValue() + 1);
		}
		
		unitAffectedTiles.put(loc, i);
		playerAffectedTiles.put(loc, new Boolean(u.getPlayer().isHuman()));
	}

	@Override
	public void visitWorkerGroup(WorkerGroup wg) {
		// TODO Auto-generated method stub
		GameTile loc = wg.location();
		WorkerID wgid = new WorkerID(wg);
		
		Set<WorkerID> s;
		
		if (!workerAffectedTiles.containsKey(loc))
		{
			s = new HashSet<WorkerID>();
		}
		else
		{
			s = workerAffectedTiles.get(loc);
		}
		s.add(wgid);
		
		workerAffectedTiles.put(loc, s);
		playerAffectedTiles.put(loc, new Boolean(wg.getPlayer().isHuman()));
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
