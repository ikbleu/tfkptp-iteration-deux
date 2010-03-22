/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package src.view;

//import src.model.TypeCode;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.Color;


/**
 *
 * @author spock
 */
class GraphicsTableSingleton {

    HashMap< String, BufferedImage > graphics;
    private BufferedImage unvisible = new BufferedImage( 200, 173,
                                        BufferedImage.TYPE_INT_ARGB );

    private BufferedImage shrouded = new BufferedImage( 200, 173,
                                        BufferedImage.TYPE_INT_ARGB );

    private Graphics2D graphiz = unvisible.createGraphics();

    private Graphics2D graphize = shrouded.createGraphics();

    private GraphicsTableSingleton(){
        graphics = new HashMap<String, BufferedImage>();
        try{
            graphiz.setColor(new Color(0,0,0, 2));
            graphiz.fillRect(0,0,200,173);
            graphize.setColor(Color.BLACK);
            graphize.fillRect(0,0,200,173);

            graphics.put( "shrouded", shrouded);
            graphics.put( "non visible", unvisible);

            graphics.put( "hud", ImageIO.read(new File("artwork/unicornhud.png")));

            graphics.put( "Stars", ImageIO.read(new File("artwork/rbStar.jpg")));
            graphics.put( "rbDust", ImageIO.read(new File("artwork/rbDust.jpg")));
            graphics.put( "Dreams", ImageIO.read(new File("artwork/rbDream.jpg")));

            graphics.put( "instanceRanged", ImageIO.read(new File("artwork/rbuni.jpg")));
            graphics.put( "instanceMelee", ImageIO.read(new File("artwork/rbdolph.jpg")));
            graphics.put( "instanceExplorer", ImageIO.read(new File("artwork/narwhal.jpg")));
            graphics.put( "instanceColonist", ImageIO.read(new File("artwork/rainbowBrite.jpg")));
            graphics.put( "instanceCapital", ImageIO.read(new File("artwork/castle.jpg")));
            graphics.put( "instanceFortress", ImageIO.read(new File("artwork/fortt.jpg")));
            graphics.put( "instanceTower", ImageIO.read(new File("artwork/towerr.jpg")));
            graphics.put( "instanceFarm", ImageIO.read(new File("artwork/barnn.jpg")));
            graphics.put( "instancePowerPlant", ImageIO.read(new File("artwork/powerplantt.jpg")));
            graphics.put( "instanceUniversity", ImageIO.read(new File("artwork/univ.jpg")));
            //graphics.put( "instanceMine", ImageIO.read(new File("artwork/minee.gif")));

            graphics.put( "instanceCapitalB", ImageIO.read(new File("artwork/castleB.jpg")));
            graphics.put( "instanceFortressB", ImageIO.read(new File("artwork/fortB.jpg")));
            graphics.put( "instanceTowerB", ImageIO.read(new File("artwork/towerB.jpg")));
            graphics.put( "instanceFarmB", ImageIO.read(new File("artwork/barnB.jpg")));
            graphics.put( "instancePowerPlantB", ImageIO.read(new File("artwork/powerplantB.jpg")));
            graphics.put( "instanceUniversityB", ImageIO.read(new File("artwork/univB.jpg")));
            //graphics.put( "instanceMineB", ImageIO.read(new File("artwork/mineB.gif")));
            System.out.println("greetings");

            graphics.put( "instanceRangedB", ImageIO.read(new File("artwork/rbuniB.jpg")));
            graphics.put( "instanceMeleeB", ImageIO.read(new File("artwork/rbdolphB.jpg")));
            graphics.put( "instanceExplorerB", ImageIO.read(new File("artwork/narwhalB.jpg")));
            graphics.put( "instanceColonistB", ImageIO.read(new File("artwork/rainbowBriteB.jpg")));

            graphics.put( "Command", ImageIO.read(new File("artwork/rbCommand.jpg")));
            graphics.put( "GRASSLAND", ImageIO.read(new File("artwork/grassland.png")));
            graphics.put( "SPARSE_FOREST", ImageIO.read(new File("artwork/sparseforest.png")));
            graphics.put( "WATER", ImageIO.read(new File("artwork/water.png")));
            graphics.put( "OUTER_SPACE", ImageIO.read(new File("artwork/pitofdeath.png")));
            graphics.put( "base", ImageIO.read(new File("artwork/castle.jpg")));
            graphics.put( "Overview", ImageIO.read(new File("artwork/overview.png")));

            graphics.put( "DefendN", ImageIO.read(new File("artwork/arrows/DefendN.png")));
            graphics.put( "DefendNW", ImageIO.read(new File("artwork/arrows/DefendNW.png")));
            graphics.put( "DefendSW", ImageIO.read(new File("artwork/arrows/DefendSW.png")));
            graphics.put( "DefendS", ImageIO.read(new File("artwork/arrows/DefendS.png")));
            graphics.put( "DefendSE", ImageIO.read(new File("artwork/arrows/DefendSE.png")));
            graphics.put( "DefendNE", ImageIO.read(new File("artwork/arrows/DefendNE.png")));

            graphics.put( "AttackN", ImageIO.read(new File("artwork/arrows/AttackN.png")));
            graphics.put( "AttackNW", ImageIO.read(new File("artwork/arrows/AttackNW.png")));
            graphics.put( "AttackSW", ImageIO.read(new File("artwork/arrows/AttackSW.png")));
            graphics.put( "AttackS", ImageIO.read(new File("artwork/arrows/AttackS.png")));
            graphics.put( "AttackSE", ImageIO.read(new File("artwork/arrows/AttackSE.png")));
            graphics.put( "AttackNE", ImageIO.read(new File("artwork/arrows/AttackNE.png")));

            graphics.put( "MoveN", ImageIO.read(new File("artwork/arrows/MoveN.png")));
            graphics.put( "MoveNW", ImageIO.read(new File("artwork/arrows/MoveNW.png")));
            graphics.put( "MoveSW", ImageIO.read(new File("artwork/arrows/MoveSW.png")));
            graphics.put( "MoveS", ImageIO.read(new File("artwork/arrows/MoveS.png")));
            graphics.put( "MoveSE", ImageIO.read(new File("artwork/arrows/MoveSE.png")));
            graphics.put( "MoveNE", ImageIO.read(new File("artwork/arrows/MoveNE.png")));

            graphics.put( "EnergyT", ImageIO.read(new File("artwork/BlackStar.PNG")));
            graphics.put( "FoodT", ImageIO.read(new File("artwork/circle.png")));
            graphics.put( "OreT", ImageIO.read(new File("artwork/DarkMoon.PNG")));

            graphics.put( "itemDamage", ImageIO.read(new File("artwork/damageItem.png")));
            graphics.put( "itemHeal", ImageIO.read(new File("artwork/healingItem.jpg")));
            graphics.put( "itemResource", ImageIO.read(new File("artwork/resourceItem.jpg")));
            graphics.put( "itemObstacle", ImageIO.read(new File("artwork/obstacleItem.jpg")));

            graphics.put( "skull xbones", ImageIO.read(new File("artwork/skull.jpg")));
            graphics.put( "red cross", ImageIO.read(new File("artwork/cross.jpg")));

            graphics.put( "space", ImageIO.read(new File("artwork/space2.jpg")));
        }
        catch(Exception e){
            System.out.println("Massive Fail");
        }
    }

    BufferedImage getGraphic(String key){
        return graphics.get(key);
    }
    private static class GraphicsTableSingletonHolder {
        private static final GraphicsTableSingleton ONEANDONLY =
                                            new GraphicsTableSingleton();
    }

    public static GraphicsTableSingleton getInstance() {
        return GraphicsTableSingletonHolder.ONEANDONLY;
    }
}