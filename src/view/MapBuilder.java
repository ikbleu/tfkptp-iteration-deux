/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package src.view;

import src.model.enums.DecalType;
import src.model.enums.Visibility;
import src.model.enums.Direction;

import java.util.Map;
/**
 *
 * @author spock
 */
public interface MapBuilder {
    public void setStructure(String structure);
    public void addWorkerGroup(String type, int workers);
    public void setSoldiersInside(int soldiers);
    public void setResources(Map<String, Integer> resources);
    public void setItem(String item);
    public void setDecal(DecalType decal);
    public void addRallyPoint(int rallyPoint, Direction d, String status);
    public void setVisibility(Visibility vis);
    public void setTerrain(String terrain);
    public void setIndividualUnits(int individualUnits);
    public void setPlayer(String player);
}
