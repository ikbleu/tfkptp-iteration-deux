/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package src.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author spock
 */
 class Overview extends BaseImage{
    String title;
    String[] list;
    String subTitle;
    private Graphics2D graphix;
    private Font f1 = new Font( "Times Roman", Font.BOLD, 47 );
    private Font f2 = new Font( "Times Roman", Font.BOLD, 17 );

    Overview(int wid, int hei){
    	
    	title = "TestTitle";
    	subTitle = "TestSub";

        imageWidth = 604;
        imageHeight = 517;
        imageBuffer = new BufferedImage( imageWidth, imageHeight,
                                        BufferedImage.TYPE_INT_ARGB );
        graphix = imageBuffer.createGraphics();
        refreshImage();
    }

    void setTitle(String t){
        title = t;
    }

    void setList(String[] li){
        list = li;
    }

    void setSubTitle(String foot){
        subTitle = foot;
    }

    public void refreshImage(){
    	
        graphix.drawImage(graphicsTable.getGraphic("Overview"), 0, 0, null);
        
        graphix.setFont(f1);
        if(title!=null)
        	graphix.drawString(title, 130, 100);

        graphix.setFont(f2);
        if(subTitle!=null)
        	graphix.drawString(subTitle, 60, 450);
    }
}
