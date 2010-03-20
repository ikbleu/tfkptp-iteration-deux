/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package src.view;

import java.awt.image.BufferedImage;

import src.model.interfaces.Displayable;

/**
 *
 * @author spock
 */
 class HUD extends BaseImage{
    private MiniMap minimap;

    HUD(int wid, int hei){
        imageWidth = wid;
        imageHeight = hei;
        imageBuffer = new BufferedImage( wid, hei, BufferedImage.TYPE_INT_ARGB );
        minimap = new MiniMap();
        refreshImage();
    }

    void setStatusOverview(Displayable[] statusOverview){

    }

    void updateMiniMap(){
    }

    void refreshImage(){
    }

}
