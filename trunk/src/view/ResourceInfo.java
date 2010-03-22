/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package src.view;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Font;
import java.util.Map;
/**
 *
 * @author spock
 */
 class ResourceInfo extends BaseImage{

     private String[] resourceTypes;
     private int[] resources;
     private Graphics2D graphix;
     private int resourceWidth;
     private int resImg = 60;
     private Font f1 = new Font( "Times Roman", Font.BOLD, 17 );

     ResourceInfo(){
        resourceTypes = new String[]{"Stars", "rbDust", "Dreams"};
        resources = new int[]{0,0,0};
        imageWidth = 330;
        imageHeight = 90;
        resourceWidth = 110;
        imageBuffer = new BufferedImage( imageWidth, imageHeight,
                                        BufferedImage.TYPE_INT_ARGB );
        graphix = imageBuffer.createGraphics();
        graphix.setFont(f1);
        refreshImage();
     }
     
     void setResources(Map<String, Integer> resource){
        resources[0] = resource.get("rscEnergy");
        resources[1] = resource.get("rscMetal");
        resources[2] = resource.get("rscFood");
        refreshImage();
     }

     void refreshImage(){
         graphix.setColor(Color.PINK);
         graphix.fillRect(0,0,imageWidth, imageHeight);
         for(int j = 0; j < resourceTypes.length; ++j){
             graphix.setColor(Color.BLACK);
             graphix.drawRect(resourceWidth*j, 0, resourceWidth*(j+1), imageHeight);
             graphix.drawImage(graphicsTable.getGraphic(resourceTypes[j]),
                               5+resourceWidth*j, 15, null);
             graphix.drawRect(5+resourceWidth*j, 15, resImg, resImg);
             graphix.setColor(Color.MAGENTA);
             graphix.drawString(""+resources[j], resImg+10+(resourceWidth)*j,50);
         }
     }
}
