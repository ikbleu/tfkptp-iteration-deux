/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package src.view;

import java.awt.image.BufferedImage;

/**
 *
 * @author spock
 */
public class MainScreen extends HasAnImage{
    private ViewPort viewport;
    private HUD hud;
    private CommandSelection commandSelection;
    private ResourceInfo resourceInfo;

    MainScreen(int wid, int hei){
        imageWidth = wid;
        imageHeight = hei;
        imageBuffer = new BufferedImage( wid, hei, BufferedImage.TYPE_INT_ARGB );
        //viewport = new ViewPort(tile);
        //hud = new HUD();
        refreshImage();
    }

    void updateViewPort(){
    }

    void updateHUD(){
    }

    void refreshImage(){

    }
}
