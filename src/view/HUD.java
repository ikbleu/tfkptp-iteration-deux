/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package src.view;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Color;
import java.util.Map;
import java.util.HashMap;

import src.model.interfaces.Displayable;
import src.model.interfaces.vInstance;

/**
 *
 * @author spock
 */
 class HUD extends BaseImage{
    private MiniMap minimap;
    private Graphics2D graphix;
    private Map<String, Integer> soTypeAndHealth;
    private Map<String, Integer> soStats;


    HUD(int wid, int hei){
        imageWidth = wid;
        imageHeight = hei;
        imageBuffer = new BufferedImage( wid, hei, BufferedImage.TYPE_INT_ARGB );
        minimap = new MiniMap();
        refreshImage();
    }

    void setStatusOverview(Displayable[] statusOverview){
        String type = null;
        String instance = null;
        String context = null;
        soStats = null;
        soTypeAndHealth = null;
        for(int i = 0;statusOverview != null && i < statusOverview.length;++i){
            JackTheViewVisitor jack = new JackTheViewVisitor();
            statusOverview[i].accept(jack);
            context = jack.infoType();
            if(context.equals("Type")){
                type = jack.info();
            }
            else if(context.equals("Instance") && type!=null){
                instance = jack.info() + jack.id();
                if(("RallyPoint").equals(jack.info())){
                    soTypeAndHealth = jack.rpTypeAndHealth();
                }
                else{
                    soTypeAndHealth = new HashMap<String, Integer>();
                    soTypeAndHealth.put(jack.info() , jack.health());
                    soStats = jack.stats();
                }
            }
        }
    }

    void updateMiniMap(){
    }

    void refreshImage(){
    	imageBuffer = graphicsTable.getGraphic("hud");
        graphix = imageBuffer.createGraphics();
        graphix.setColor(Color.GREEN);
        

    }

}
