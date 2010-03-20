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
 class Overview extends HasAnImage{
    String title;
    String[] list;
    String footer;

    Overview(int wid, int hei){
        imageWidth = wid;
        imageHeight = hei;
        imageBuffer = new BufferedImage( wid, hei, BufferedImage.TYPE_INT_ARGB );
        refreshImage();
    }

    void setTitle(String t){
        title = t;
    }

    void setList(String[] li){
        list = li;
    }

    void setFooter(String foot){
        footer = foot;
    }

    void refreshImage(){
    }
}
