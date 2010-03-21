package src.model;

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
import src.model.interfaces.HasPlayerVisitor;

public class VisibilityMap implements HasPlayerVisitor
{
	Map<GameTile, Integer> unitsPerTile;
	Map<GameTile, String> structureOnTile;
	Map<GameTile, Set<RPPointingID> > rallyPointsOnTile;
	Map<GameTile, Set<WorkerID> > workersOnTile;
	Map<GameTile, Map<String, Integer> > resourcesOnTile;
	Set<GameTile> exploredTiles;
	Map<GameTile, Visibility> seenTiles;
	Map<GameTile, String> playerOwningTile;

	@Override
	public void visitRallyPoint(RallyPoint rp) {
		// TODO Auto-generated method stub
		
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
