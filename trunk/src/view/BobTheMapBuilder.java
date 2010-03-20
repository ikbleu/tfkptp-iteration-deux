/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package src.view;

import src.model.interfaces.Displayable;
import src.model.enums.DecalType;
import src.model.enums.Visibility;
import src.model.enums.Direction;

import java.awt.image.BufferedImage;
import java.util.LinkedList;



/**
 *
 * @author spock
 */
class BobTheMapBuilder implements MapBuilder{
        String structure;
        int workers;
        int idles;
        int breeders;
        int soldiers;
        int individualUnits;
        int[] harvesters;
        int[] resources;
        String item;
        String decal;
        LinkedList<RallyPointV> rallyPoints;
        String visibility;
        String terrain;
        String player;
        BufferedImage imageBuffer;
        EnumTableSingleton enumT;
        GraphicsTableSingleton graphicsT;


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
        rallyPoints = null;
        visibility = null;
        terrain = null;
        individualUnits = -1;
        player = null;
    }



    public void setStructure(String structure){
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
    public void addRallyPoint(int rallyPoint, Direction d, String status){
        rallyPoints.add(new RallyPointV(rallyPoint, d, status));
    }
    public void setVisibility(Visibility vis){
        this.visibility = enumT.getString(vis);
    }
    public void setTerrain(String terrain){
        this.terrain = terrain;
    }
    public void setIndividualUnits(int individualUnits){
        this.individualUnits = individualUnits;
    }

    public void setPlayer(String player){
        this.player = player;
    }

    void buildMe(){
        if(terrain!=null){
            imageBuffer = graphicsT.getGraphic(terrain);
        }
    }

    private class RallyPointV{
        int rallyPoint;
        Direction direction;
        String status;

        RallyPointV(int rP, Direction di, String sta){
            rallyPoint = rP;
            direction = di;
            status = sta;
        }
    }

}

