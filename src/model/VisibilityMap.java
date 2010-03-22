package src.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import src.model.enums.DecalType;
import src.model.enums.Direction;
import src.model.enums.Visibility;
import src.model.instances.Item;
import src.model.instances.RallyPoint;
import src.model.instances.Structure;
import src.model.instances.Unit;
import src.model.instances.WorkerGroup;
import src.model.interfaces.GameTile;
import src.model.interfaces.HasPlayer;
import src.model.interfaces.HasPlayerVisitor;
import src.view.MapBuilder;

/**
 * 
 * @author Adam
 *
 */

public class VisibilityMap implements HasPlayerVisitor
{
	private Map<GameTile, Integer> unitsPerTile;
	private Map<GameTile, StructID> structureOnTile;
	private Map<GameTile, Set<RPPointingID> > rallyPointsOnTile;
	private Map<GameTile, Set<WorkerID> > workersOnTile;
	private Map<GameTile, Boolean> playerOwningTile;
	
	private Map<GameTile, String> itemOnTile;
	private Map<GameTile, Map<String, Integer> > resourcesOnTile;
	private Map<GameTile, DecalType> exploredTiles;
	private Map<GameTile, Visibility> seenTiles;
	
	
	private Map<GameTile, Integer> unitAffectedTiles;
	private Map<GameTile, Set<RPPointingID> > rallyPointAffectedTiles;
	private Map<GameTile, StructID> structureAffectedTiles;
	private Map<GameTile, Boolean> playerAffectedTiles;
	private Map<GameTile, Set<WorkerID> > workerAffectedTiles;
	
	private final boolean DEBUGGING = true;

	public VisibilityMap()
	{
		unitsPerTile = new HashMap<GameTile, Integer>();
		structureOnTile = new HashMap<GameTile, StructID>();
		rallyPointsOnTile = new HashMap<GameTile, Set<RPPointingID> >();
		workersOnTile = new HashMap<GameTile, Set<WorkerID> >();
		playerOwningTile = new HashMap<GameTile, Boolean>();
		
		itemOnTile = new HashMap<GameTile, String>();
		resourcesOnTile = new HashMap<GameTile, Map<String, Integer> >();
		exploredTiles = new HashMap<GameTile, DecalType>();
		
		seenTiles = new HashMap<GameTile, Visibility>();	
		
		unitAffectedTiles = new HashMap<GameTile, Integer>();
		structureAffectedTiles = new HashMap<GameTile, StructID>();
		rallyPointAffectedTiles = new HashMap<GameTile, Set<RPPointingID> >();
		workerAffectedTiles = new HashMap<GameTile, Set<WorkerID> >();
		playerAffectedTiles = new HashMap<GameTile, Boolean>();
	}
	
	public void updateVisibility(Set<GameTile> newVisibles, Set<HasPlayer> hasPlayers,
									List<Item> items)
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
		
		updateResources(newVisibles);
		updateItems(newVisibles, items);
		
		unitsPerTile.putAll(unitAffectedTiles);
		structureOnTile.putAll(structureAffectedTiles);
		rallyPointsOnTile.putAll(rallyPointAffectedTiles);
		workersOnTile.putAll(workerAffectedTiles);
		playerOwningTile.putAll(playerAffectedTiles);
		
		unitAffectedTiles.clear();
		structureAffectedTiles.clear();
		rallyPointAffectedTiles.clear();
		workerAffectedTiles.clear();
		playerAffectedTiles.clear();
	}
	
	private void updateItems(Set<GameTile> newVisibles, List<Item> items) {
		// TODO Auto-generated method stub
		Iterator<GameTile> i = newVisibles.iterator();
		while (i.hasNext())
		{
			GameTile loc = i.next();
			itemOnTile.remove(loc);
		}
		
		for (int j = 0; j < items.size(); j++)
		{
			Item x = items.get(j);
			if (newVisibles.contains(x.location()))
			{
				itemOnTile.put(x.location(), x.token());
			}
		}
	}

	private void updateResources(Set<GameTile> visibles)
	{	
		Iterator<GameTile> i = visibles.iterator();
		while (i.hasNext())
		{
			GameTile loc = i.next();
			
			if (exploredTiles.containsKey(loc))
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
		if (u.isInRallyPoint())
			return;
		
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
	
	public void explore(Map<GameTile, DecalType> tileList)
	{
		exploredTiles.putAll(tileList);
		
	}

	public void acceptBuilder(MapBuilder mapBuilder, GameTile location)
	{
		// TODO Auto-generated method stub
		if (unitsPerTile.containsKey(location))
		{
			mapBuilder.setIndividualUnits(unitsPerTile.get(location).intValue());
		}
		
		if (structureOnTile.containsKey(location))
		{
			mapBuilder.setStructure(structureOnTile.get(location).getType());
			mapBuilder.setSoldiersInside(structureOnTile.get(location).getNumSoldiers());
		}
		
		if (rallyPointsOnTile.containsKey(location))
		{
			Set<RPPointingID> rpIDs = rallyPointsOnTile.get(location);
			
			Iterator<RPPointingID> i = rpIDs.iterator();
			
			while (i.hasNext())
			{
				RPPointingID rpid = i.next();
				mapBuilder.addRallyPoint(rpid.getRallyID(), rpid.getFacingDirection(), rpid.getAction());
			}
			
		}
		
		if (workersOnTile.containsKey(location))
		{
			Iterator<WorkerID> i = workersOnTile.get(location).iterator();
			
			while (i.hasNext())
			{
				WorkerID wid = i.next();
				mapBuilder.addWorkerGroup(wid.groupType(), wid.numWorkers());
			}
		}
		
		if (playerOwningTile.containsKey(location))
		{
			if (playerOwningTile.get(location).booleanValue())
				mapBuilder.setPlayer("Human");
			else
				mapBuilder.setPlayer("Enemy");
		}
		
		if (seenTiles.containsKey(location))
		{
			mapBuilder.setVisibility(seenTiles.get(location));
		}
		else
		{
			mapBuilder.setVisibility(Visibility.SHROUDED);
		}
		
		if (resourcesOnTile.containsKey(location))
		{
			mapBuilder.setResources(resourcesOnTile.get(location));
		}
		
		if (itemOnTile.containsKey(location))
		{
			mapBuilder.setItem(itemOnTile.get(location));
		}
		
		if (exploredTiles.containsKey(location))
		{
			mapBuilder.setDecal(exploredTiles.get(location));
		}
		
	}
}
