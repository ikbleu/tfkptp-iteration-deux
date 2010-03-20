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
    ViewPort viewport;
    HUD hud;

    MainScreen(int wid, int hei){
        imageWidth = wid;
        imageHeight = hei;
        imageBuffer = new BufferedImage( wid, hei, BufferedImage.TYPE_INT_ARGB );
        viewport = new ViewPort();
        hud = new HUD(wid, (hei));
        refreshImage();
    }

    void updateViewPort(){
    }

    void updateHUD(){
    }

    void refreshImage(){

    }
}
