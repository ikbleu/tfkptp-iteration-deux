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
import java.util.HashMap;
import java.util.Map;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.RenderingHints;
import java.awt.Font;


/**
 *
 * @author spock
 */
class BobTheMapBuilder implements MapBuilder{
        private String structure;
        private int soldiers;
        private int individualUnits;
        private Map<String, Integer> resources = new HashMap<String, Integer>();
        private HashMap<String, Integer> workerGroups = new HashMap<String, Integer>();
        private String item;
        private String decal;
        private LinkedList<RallyPointV> rallyPoints = new LinkedList<RallyPointV>();
        private String visibility;
        private String terrain;
        private String player;
        private BufferedImage imageBuffer;
        private EnumTableSingleton enumT = EnumTableSingleton.getInstance();
        private GraphicsTableSingleton graphicsT = GraphicsTableSingleton.getInstance();
        private int idsSize = 25;
        private int imageSpacing = 2;
        private Font f1 = new Font( "Times Roman", Font.PLAIN, 13 );



        private Graphics2D graphix;

        private int centerX = 100;
        private int centerY = 87;


    BobTheMapBuilder(){
        structure = null;
        soldiers = -1;
        resources = null;
        item = null;
        decal = null;
        rallyPoints = new LinkedList<RallyPointV>();
        visibility = null;
        terrain = null;
        individualUnits = -1;
        player = null;
    }

    BobTheMapBuilder(String imfake){
        structure = "base";
        soldiers = -1;
        resources = new HashMap<String, Integer>();
        resources.put("Food", 25);
        resources.put("Ore", 25);
        resources.put("Energy", 25);

        workerGroups.put("wgIdle", 10);
        workerGroups.put("wgStaff", 10);
        workerGroups.put("wgBreeding", 10);
        workerGroups.put("wgGrain", 10);


        visibility = null;
        terrain = "Grassland";
        
    }



    public void setStructure(String structure){
        this.structure = structure;
    }
    public void setSoldiersInside(int soldiers){
        this.soldiers = soldiers;
    }
    public void setResources(Map<String, Integer> resources){
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
    public void addWorkerGroup(String type, int workers){
       workerGroups.put(type, workers);
    }
    public void setPlayer(String player){
        this.player = player;
    }

    BufferedImage buildMeViewPort(){
        if(terrain!=null){
            //imageBuffer = (graphicsT.getGraphic(terrain)).clone()
            imageBuffer = new BufferedImage(200, 173,
                                        BufferedImage.TYPE_INT_ARGB);
            graphix = imageBuffer.createGraphics();
            graphix.setRenderingHint( RenderingHints.KEY_ANTIALIASING,
                                  RenderingHints.VALUE_ANTIALIAS_ON );
            graphix.drawImage(graphicsT.getGraphic(terrain), 0, 0, null);
            graphix.setColor( Color.BLACK );
            graphix.setFont(f1);

            for(int i = 0;i<rallyPoints.size();++i){
                int spacing = 12;
                //System.out.println(rallyPoints.size());
                String imageArrow = rallyPoints.get(i).status();
                imageArrow = imageArrow + enumT.getString(rallyPoints.get(i).direction());
                graphix.drawImage(graphicsT.getGraphic(imageArrow), centerX + (int)polarX(Math.PI/5*(i), 65.0)-spacing,
                                  centerY + (-1)*(int)polarY(Math.PI/5*(i), 70.0)-spacing, null);
                graphix.setColor(Color.WHITE);
                graphix.drawString(""+rallyPoints.get(i).rallyPoint(), centerX + (int)polarX(Math.PI/5*(i), 50.0),
                                  centerY + (-1)*(int)polarY(Math.PI/5*(i), 50.0));
            }
            if(decal != null)
            graphix.drawImage(graphicsT.getGraphic(decal), centerX - 3*idsSize/2 - imageSpacing, centerY - idsSize/2 ,idsSize, idsSize, null);
            if(structure != null)
            graphix.drawImage(graphicsT.getGraphic(structure), centerX - idsSize/2, centerY - idsSize/2 , idsSize, idsSize, null);
            if(item!=null);
            graphix.drawImage(graphicsT.getGraphic(item), centerX + idsSize/2 + imageSpacing, centerY - idsSize/2 , idsSize, idsSize, null);
            if(individualUnits != -1){
                graphix.setColor(Color.WHITE);
                graphix.drawString("IU: "+individualUnits, centerX - 5*imageSpacing, centerY - idsSize );
            }
            /*if(visibility.equals("shrouded")){
                graphix.drawImage(graphicsT.getGraphic(visibility), 0, 0, null);
            }
            if(visibility.equals("non visible")){
                graphix.drawImage(graphicsT.getGraphic(visibility), 0, 0, null);
            }*/
        }
        else{
            imageBuffer = null;
        }
        structure = null;
        soldiers = -1;
        resources = new HashMap<String,Integer>();
        item = null;
        decal = null;
        rallyPoints = null;
        visibility = null;
        terrain = null;
        individualUnits = -1;
        player = null;
        rallyPoints = new LinkedList<RallyPointV>();
        return imageBuffer;
    }

    BufferedImage buildMeWorkerPort(){
        if(terrain!=null){
            imageBuffer = graphicsT.getGraphic(terrain);
            graphix = imageBuffer.createGraphics();
            graphix.setRenderingHint( RenderingHints.KEY_ANTIALIASING,
                                  RenderingHints.VALUE_ANTIALIAS_ON );
            graphix.setColor( Color.BLACK );
            graphix.setFont(f1);

            graphix.setColor(Color.BLACK);
            if(structure != null)
            graphix.drawImage(graphicsT.getGraphic(structure), centerX - idsSize/2, centerY - idsSize/2 , idsSize, idsSize, null);
            if(resources.containsKey("Energy") && resources.get("Energy")!=-1){
                graphix.setColor(Color.WHITE);
                graphix.drawImage(graphicsT.getGraphic("EnergyT"), centerX - 8*imageSpacing, centerY - idsSize - 20, 30,30, null);
                graphix.drawString(""+resources.get("Energy"), centerX - 5*imageSpacing, centerY - idsSize );          
            }
            if(resources.containsKey(("Ore")) && resources.get("Ore")!=-1){
                graphix.drawImage(graphicsT.getGraphic("OreT"), centerX - 28*imageSpacing, centerY - idsSize - 20, 30,30, null);
                graphix.drawString(""+resources.get("Ore"), centerX - 25*imageSpacing, centerY - idsSize );
            }
            if(resources.containsKey(("Food")) && resources.get("Food")!=-1){
                graphix.drawImage(graphicsT.getGraphic("FoodT"), centerX + 12*imageSpacing, centerY - idsSize - 20, 30,30, null);
                graphix.drawString(""+resources.get("Food"), centerX + 15*imageSpacing, centerY - idsSize );
            }
            if(workerGroups.containsKey(("wgGrain"))){
                graphix.drawImage(graphicsT.getGraphic("FoodT"), centerX - 8*imageSpacing, centerY + 2*idsSize - 20, 30,30, null);
                graphix.drawString(""+workerGroups.get("wgGrain"), centerX - 5*imageSpacing, centerY + 2*idsSize );
            }
            if(workerGroups.containsKey(("wgOre"))){
                graphix.drawImage(graphicsT.getGraphic("OreT"), centerX - 8*imageSpacing, centerY + 2*idsSize - 20, 30,30, null);
                graphix.drawString(""+workerGroups.get("wgOre"), centerX - 5*imageSpacing, centerY + 2*idsSize );
            }
            if(workerGroups.containsKey(("wgFuel"))){
                graphix.drawImage(graphicsT.getGraphic("EnergyT"), centerX - 8*imageSpacing, centerY + 2*idsSize - 20, 30,30, null);
                graphix.drawString(""+workerGroups.get("wgFuel"), centerX - 5*imageSpacing, centerY + 2*idsSize );
            }
            if(workerGroups.containsKey(("wgIdle"))){
                graphix.drawString("I: "+workerGroups.get("wgIdle"), centerX + 1*idsSize , centerY + 2*imageSpacing);
            }
            if(workerGroups.containsKey(("wgBreeding"))){
                graphix.drawString("B: "+workerGroups.get("wgBreeding"), centerX - 2*idsSize - 3*imageSpacing, centerY + 2*imageSpacing);
            }
            if(workerGroups.containsKey(("wgStaff"))){
                graphix.drawString("S: "+workerGroups.get("wgStaff"), centerX + 1*idsSize, centerY + 2*idsSize);
            }
            /*if(visibility.equals("shrouded")){
                graphix.drawImage(graphicsT.getGraphic(visibility), 0, 0, null);
            }
            if(visibility.equals("non visible")){
                graphix.drawImage(graphicsT.getGraphic(visibility), 0, 0, null);
            }*/

        }
        else{
            imageBuffer = null;
        }
        structure = null;
        soldiers = -1;
        resources = null;
        item = null;
        decal = null;
        rallyPoints = null;
        visibility = null;
        terrain = null;
        individualUnits = -1;
        player = null;
        workerGroups = new HashMap<String, Integer>();
        return imageBuffer;
    }

    int coSchemer(){
        int gr = 0;
        if(visibility != null && visibility.equals("non visible")){
            gr = 1;
        }
        if(visibility != null && visibility.equals("shrouded")){
            gr = 2;
        }
        return gr;
    }

    private double polarX(double angle, double length){
        return length * Math.cos(angle);
    }
    private double polarY(double angle, double length){
        return length * Math.sin(angle);
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

        String status(){
            return status;
        }
        Direction direction(){
            return direction;
        }
        int rallyPoint(){
            return rallyPoint;
        }
    }



}

