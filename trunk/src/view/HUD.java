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
 class HUD extends BaseImage{
    private MiniMap minimap;
    private String groupBox;
    private String typeBox;
    private String commandBox;
    private String instanceBox;
    private int[] resources;

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
