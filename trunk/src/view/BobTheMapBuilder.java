/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package src.view;

import src.model.interfaces.Displayable;
import src.model.enums.DecalType;
import src.model.enums.Visibility;

import java.awt.image.BufferedImage;


/**
 *
 * @author spock
 */
class BobTheMapBuilder implements MapBuilder{
        Displayable structure;
        int workers;
        int idles;
        int breeders;
        int soldiers;
        int[] harvesters;
        int[] resources;
        String item;
        String decal;
        Displayable[] rallypoints;
        String visibility;
        String terrain;
        BufferedImage imageBuffer;
        EnumTableSingleton enumT;


    BobTheMapBuilder(){
        structure = null;
        workers = -1;
        idles = -1;
        breeders = -1;
        soldiers = -1;
        harvesters = null;
        resources = null;
        item = null;
        decal = null;
        rallypoints = null;
        visibility = null;
        terrain = null;
    }



    public void setStructure(Displayable structure){
        this.structure = structure;
    }
    public void setWorkers(int workers){
        this.workers = workers;
    }
    public void setIdle(int idles){
        this.idles = idles;
    }
    public void setBreeding(int breeders){
        this.breeders = breeders;
    }
    public void setSoldiersInside(int soldiers){
        this.soldiers = soldiers;
    }
    public void setHarvesters(int[] harvesters){
        this.harvesters = harvesters;
    }
    public void setResources(int[] resources){
        this.resources = resources;
    }
    public void setItem(String item){
        this.item = item;
    }
    public void setDecal(DecalType decal){
        this.decal = enumT.getString(decal);
    }
    public void setRallyPoints(Displayable[] rallyP){
        rallypoints = rallyP;
    }
    public void setVisibility(Visibility vis){
        this.visibility = enumT.getString(vis);
    }
    public void setTerrain(String terrain){
        this.terrain = terrain;
    }
}

