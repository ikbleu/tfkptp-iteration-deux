/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package src.view;

import src.model.enums.DecalType;
import src.model.enums.Visibility;
import src.model.enums.Direction;
/**
 *
 * @author spock
 */
public interface MapBuilder {
    public void setStructure(String structure);
    public void setWorkers(int workers);
    public void setIdle(int idles);
    public void setBreeding(int breeders);
    public void setSoldiersInside(int soldiers);
    public void setHarvesters(int[] harvesters);
    public void setResources(int[] resources);
    public void setItem(String item);
    public void setDecal(DecalType decal);
    public void addRallyPoint(int rallyPoint, Direction d, String status);
    public void setVisibility(Visibility vis);
    public void setTerrain(String terrain);
    public void setIndividualUnits(int individualUnits);
    public void setPlayer(String player);
}
