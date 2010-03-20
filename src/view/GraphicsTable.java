/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package src.view;

//import src.model.TypeCode;

import java.awt.Image;
import java.util.HashMap;

import src.model.interfaces.Token;

/**
 *
 * @author spock
 */
public class GraphicsTable {

    HashMap< Token, Image > graphics;

    GraphicsTable(){

    }

    Image getGraphic(Token key){
        return graphics.get(key);
    }

}
