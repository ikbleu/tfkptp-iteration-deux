/**
 * 
 */
package src.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import src.model.instances.Instance;
import src.model.interfaces.GameTile;
import src.model.interfaces.InstanceFunction;

/**
 * @author Adam
 *
 */
public class GetVisibleTiles implements InstanceFunction
{
	private Set<GameTile> visibleTiles;
	
	public GetVisibleTiles()
	{
		visibleTiles = new HashSet<GameTile>();
	}
	
	public void execute(Instance i) {
		visibleTiles.addAll(i.getVisibleTiles());
	}
	
	public void clear()
	{
		visibleTiles.clear();
	}
	
	public Set<GameTile> getVisibleTiles()
	{
		return visibleTiles;
	}
}
