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
            graphics.put( "Unicorn", ImageIO.read(new File("artwork/rbuni.jpg")));
            graphics.put( "Dolphin", ImageIO.read(new File("artwork/rbdolph.jpg")));
            graphics.put( "UnicornB", ImageIO.read(new File("artwork/rbuniB.jpg")));
            graphics.put( "DolphinB", ImageIO.read(new File("artwork/rbdolphB.jpg")));
            graphics.put( "Command", ImageIO.read(new File("artwork/rbCommand.jpg")));
            graphics.put( "GRASSLAND", ImageIO.read(new File("artwork/grassland.png")));
            graphics.put( "SPARSE_FOREST", ImageIO.read(new File("artwork/sparseforest.png")));
            graphics.put( "WATER", ImageIO.read(new File("artwork/water.png")));
            graphics.put( "OUTER_SPACE", ImageIO.read(new File("artwork/pitofdeath.png")));
            graphics.put( "DefendN", ImageIO.read(new File("artwork/testarrow.png")));
            graphics.put( "base", ImageIO.read(new File("artwork/castle.jpg")));
            graphics.put( "Overview", ImageIO.read(new File("artwork/overview.png")));
            graphics.put( "EnergyT", ImageIO.read(new File("artwork/BlackStar.PNG")));
            graphics.put( "FoodT", ImageIO.read(new File("artwork/circle.png")));
            graphics.put( "OreT", ImageIO.read(new File("artwork/DarkMoon.PNG")));
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