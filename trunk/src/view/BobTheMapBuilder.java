/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package src.view;

import src.model.interfaces.Displayable;
import src.model.enums.DecalType;
import src.model.enums.ItemType;
import src.model.enums.TerrainType;
import src.model.enums.Visibility;


/**
 *
 * @author spock
 */
class BobTheMapBuilder implements MapBuilder{
    Displayable structure = null;
    int workers = -1;
    int idles = -1;
    int breeders = -1;
    int soldiers = -1;
    int[] harvesters = null;
    int[] resources = null;
    String item = null;
    String decal = null;
    Displayable[] rallypoints = null;
    String visibility = null;
    String terrain = null;


    public void setStructure(Displayable structure){

    }
    public void setWorkers(int workers){

    }
    public void setIdle(int idles){

    }
    public void setBreeding(int breeders){

    }
    public void setSoldiersInside(int soldiers){

    }
    public void setHarvesters(int[] harvesters){

    }
    public void setResources(int[] resources){

    }
    public void setItem(ItemType item){

    }
    public void setDecal(DecalType decal){

    }
    public void setRallyPoints(Displayable[] rallyPoints){

    }
    public void setVisibility(Visibility vis){

    }
    public void setTerrain(TerrainType terrain){

    }
}

