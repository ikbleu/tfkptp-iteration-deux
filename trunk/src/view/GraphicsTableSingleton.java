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


/**
 *
 * @author spock
 */
class GraphicsTableSingleton {

    HashMap< String, BufferedImage > graphics;

    private GraphicsTableSingleton(){
        graphics = new HashMap<String, BufferedImage>();
        try{
            graphics.put( "hud", ImageIO.read(new File("artwork/unicornhud.png")));

            graphics.put( "Stars", ImageIO.read(new File("artwork/rbStar.jpg")));
            graphics.put( "rbDust", ImageIO.read(new File("artwork/rbDust.jpg")));
            graphics.put( "Dreams", ImageIO.read(new File("artwork/rbDream.jpg")));

            graphics.put( "Ranged", ImageIO.read(new File("artwork/rbuni.jpg")));
            graphics.put( "Melee", ImageIO.read(new File("artwork/rbdolph.jpg")));
            graphics.put( "Explorer", ImageIO.read(new File("artwork/narwhal.jpg")));
            graphics.put( "Colonist", ImageIO.read(new File("artwork/rainbowBrite.jpg")));

            graphics.put( "RangedB", ImageIO.read(new File("artwork/rbuniB.jpg")));
            graphics.put( "MeleeB", ImageIO.read(new File("artwork/rbdolphB.jpg")));
            graphics.put( "ExplorerB", ImageIO.read(new File("artwork/narwhalB.jpg")));
            graphics.put( "ColonistB", ImageIO.read(new File("artwork/rainbowBriteB.jpg")));

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
            graphics.put( "itemHealing", ImageIO.read(new File("artwork/healingItem.jpg")));
            graphics.put( "itemResource", ImageIO.read(new File("artwork/resourceItem.jpg")));
            graphics.put( "itemObstacle", ImageIO.read(new File("artwork/obstacleItem.jpg")));
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