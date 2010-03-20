/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package src.view;

import src.model.interfaces.Displayable;
import src.model.enums.DecalType;
import src.model.enums.TerrainType;
import src.model.enums.Visibility;
import src.model.enums.ItemType;
/**
 *
 * @author spock
 */
public interface MapBuilder {
    public void setStructure(Displayable structure);
    public void setWorkers(int workers);
    public void setIdle(int idles);
    public void setBreeding(int breeders);
    public void setSoldiersInside(int soldiers);
    public void setHarvesters(int[] harvesters);
    public void setResources(int[] resources);
    public void setItem(ItemType item);
    public void setDecal(DecalType decal);
    public void setRallyPoints(Displayable[] rallyPoints);
    public void setVisibility(Visibility vis);
    public void setTerrain(TerrainType terrain);
}
