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
	private Map<GameTile, StructID> structureOnTile;
	private Map<GameTile, Set<RPPointingID> > rallyPointsOnTile;
	private Map<GameTile, Set<WorkerID> > workersOnTile;
	private Map<GameTile, Map<String, Integer> > resourcesOnTile;
	private Set<GameTile> exploredTiles;
	private Map<GameTile, Visibility> seenTiles;
	private Map<GameTile, Boolean> playerOwningTile;
	
	private Map<GameTile, Integer> unitAffectedTiles;
	private Map<GameTile, Set<RPPointingID> > rallyPointAffectedTiles;
	private Map<GameTile, StructID> structureAffectedTiles;
	private Map<GameTile, Boolean> playerAffectedTiles;
	private Map<GameTile, Set<WorkerID> > workerAffectedTiles;

	public VisibilityMap()
	{
		unitsPerTile = new HashMap<GameTile, Integer>();
		structureOnTile = new HashMap<GameTile, StructID>();
		rallyPointsOnTile = new HashMap<GameTile, Set<RPPointingID> >();
		workersOnTile = new HashMap<GameTile, Set<WorkerID> >();
		resourcesOnTile = new HashMap<GameTile, Map<String, Integer> >();
		exploredTiles = new HashSet<GameTile>();
		playerOwningTile = new HashMap<GameTile, Boolean>();
		
		unitAffectedTiles = new HashMap<GameTile, Integer>();
		structureAffectedTiles = new HashMap<GameTile, StructID>();
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
	
	private void updateResources()
	{
		Set<GameTile> tiles = seenTiles.keySet();
		
		Iterator<GameTile> i = tiles.iterator();
		while (i.hasNext())
		{
			GameTile loc = i.next();
			
			if (exploredTiles.contains(loc))
			{
				resourcesOnTile.put(loc, loc.getResources());
				
			}
			else
			{
				Map<String, Integer> m = resourcesOnTile.get(loc);
				Iterator<String> j = m.keySet().iterator();
				while (j.hasNext())
					m.put(j.next(), new Integer(-1));
				resourcesOnTile.put(loc, m);
			}
		}
	}

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

	public void visitStructure(Structure s)
	{
		GameTile loc = s.location();
		
		structureAffectedTiles.put(loc, new StructID(s.token(),s.getStat("numSoldiers")));
		playerAffectedTiles.put(loc, new Boolean(s.getPlayer().isHuman()));		
	}

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

	public void visitWorkerGroup(WorkerGroup wg)
	{
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

	private class StructID
	{
		private String type;
		private int numSoldiers;
		
		public StructID(String s, int n)
		{
			type = s;
			numSoldiers = n;
		}
		
		public String getType()
		{
			return type;
		}
		
		public int getNumSoldiers()
		{
			return numSoldiers;
		}
	}
	
	public void explore(List<GameTile> tileList)
	{
		exploredTiles.addAll(tileList);
		
	}
}
