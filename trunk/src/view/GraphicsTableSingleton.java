/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package src.view;

//import src.model.TypeCode;

import java.awt.Image;
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