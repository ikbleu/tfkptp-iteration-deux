package src.model;

import java.util.Map;
import java.util.Set;

import src.model.enums.DecalType;
import src.model.interfaces.Clock;
import src.model.interfaces.GameTile;
import src.model.interfaces.HasPlayer;
import src.model.interfaces.InstanceHolder;
import src.model.interfaces.ItemVisibilityHolder;
import src.model.interfaces.SakuraMap;
import src.model.interfaces.Tickable;
import src.view.MapBuilder;

public class VisibilityManager implements SakuraMap, Tickable
{
	Player player;
	VisibilityMap visibleMap;
	GameMap gameMap;
	HasPlayerManager playerStuff;
	InstanceHolder playerInstances;
	ItemVisibilityHolder items;
	
	public VisibilityManager(Player player, GameMap map, HasPlayerManager m, Clock c)
	{
		this.player = player;
		gameMap = map;
		visibleMap = new VisibilityMap();
		playerStuff = m;
		playerInstances = player.instanceHolder();
		try
		{
			c.register("VisUpdate", this);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	public void update()
	{
		GetVisibleTiles gvt = new GetVisibleTiles();
		playerInstances.applyToAll(gvt, player);
		
		Set<HasPlayer> hasPlayers = playerStuff.getThingsIn(gvt.getVisibleTiles());
		visibleMap.updateVisibility(gvt.getVisibleTiles(), hasPlayers, items.getAllItems());
	}
	
	@Override
	public void build(MapBuilder[][] b) {
		// TODO Auto-generated method stub
		for (int i = 0; i < b.length; i++)
			for (int j = 0; j < b[i].length;j++)
			{
				GameTile loc = gameMap.acceptBuilder(b[i][j], i, j);
				if (loc != null)
				{
					visibleMap.acceptBuilder(b[i][j], loc);
				}
			}

	}

	public void exploreTiles(Map<GameTile, DecalType> exploredTiles)
	{
		visibleMap.explore(exploredTiles);
	}
	
	@Override
	public int mapHeight() {
		
		return gameMap.getDiameter();
	}

	@Override
	public int mapWidth() {
		return gameMap.getDiameter();
	}

	@Override
	public Map<String, Integer> getPlayerResources()
	{
		return player.resourceCount();
	}

	@Override
	public void tick()
	{
		update();
	}

}
