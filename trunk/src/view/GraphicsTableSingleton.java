/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package src.view;

//import src.model.TypeCode;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.HashMap;


/**
 *
 * @author spock
 */
class GraphicsTableSingleton {

    HashMap< String, BufferedImage > graphics;

    private GraphicsTableSingleton(){
      
    }

    BufferedImage getGraphic(String key){
        return graphics.get(key);
    }
    private static class GraphicsTableSingletonHolder {
        private static final GraphicsTableSingleton INSTANCE =
                                            new GraphicsTableSingleton();
    }

    public static GraphicsTableSingleton getInstance() {
        return GraphicsTableSingletonHolder.INSTANCE;
    }

}
/*
 public class Singleton {
    //Private constructor prevents instantiation from other classes
   private Singleton() {}

    // SingletonHolder is loaded on the first execution of Singleton.getInstance()
    // or the first access to SingletonHolder.INSTANCE, not before.

   private static class SingletonHolder {
     private static final Singleton INSTANCE = new Singleton();
   }

   public static Singleton getInstance() {
     return SingletonHolder.INSTANCE;
   }
 }
 */
