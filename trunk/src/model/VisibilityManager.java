package src.model;

import java.util.List;

import src.model.interfaces.GameTile;
import src.model.interfaces.InstanceHolder;
import src.model.interfaces.SakuraMap;
import src.view.MapBuilder;

public class VisibilityManager implements SakuraMap
{
	VisibilityMap visibleMap;
	GameMap gameMap;
	HasPlayerManager playerStuff;
	InstanceHolder playerInstances;
	
	public VisibilityManager(Player player, GameMap map, HasPlayerManager m)
	{
		gameMap = map;
		visibleMap = new VisibilityMap();
		playerStuff = m;
		playerInstances = player.instanceHolder();
	}
	
	public void update()
	{
		GetVisibleTiles gvt = new GetVisibleTiles();
		playerInstances.applyToAll(gvt);
		
		playerStuff.getThingsIn(gvt.getVisibleTiles());
	}
	
	@Override
	public void build(MapBuilder[][] b) {
		// TODO Auto-generated method stub

	}

	public void exploreTiles(List<GameTile> tileList)
	{
		visibleMap.explore(tileList);
	}
	
	@Override
	public int mapHeight() {
		
		return gameMap.getDiameter();
	}

	@Override
	public int mapWidth() {
		return gameMap.getDiameter();
	}

}
