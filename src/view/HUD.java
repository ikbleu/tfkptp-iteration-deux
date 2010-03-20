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
public class HUD extends BaseImage{
    MiniMap minimap;
    String groupBox;
    String typeBox;
    String commandBox;
    String instanceBox;
    int[] resources;

    HUD(int wid, int hei){
        imageWidth = wid;
        imageHeight = hei;
        imageBuffer = new BufferedImage( wid, hei, BufferedImage.TYPE_INT_ARGB );
        minimap = new MiniMap();
        refreshImage();
    }

    void setResources(int[] res){
        resources = res;
    }

    void setGroupBox(String group){
        groupBox = group;
    }

    void setTypeBox(String type){
        typeBox = type;
    }

    void setCommandBox(String comm){
        commandBox = comm;
    }

    void updateMiniMap(){
    }

    void refreshImage(){
    }

}
