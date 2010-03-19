/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package src.view;

//import src.model.TypeCode;

import java.awt.Image;
import java.util.HashMap;

/**
 *
 * @author spock
 */
public class GraphicsTable {

    HashMap< String, Image > graphics;

    GraphicsTable(){

    }

    Image getGraphic(String key){
        return graphics.get(key);
    }

}
