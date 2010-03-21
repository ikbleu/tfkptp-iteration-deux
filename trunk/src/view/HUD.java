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
import java.awt.BasicStroke;
import java.util.LinkedList;

import src.model.interfaces.Displayable;
import src.model.interfaces.vInstance;

/**
 *
 * @author spock
 */
 class HUD extends BaseImage{
    private MiniMap minimap;
    private Graphics2D graphix;
    private int miniAvWidth = 30;
    private int miniAvHeight = 50;
    private int healthBarHeight = 10;
    private int shift = 65;
    private LinkedList<String> soType;
    private LinkedList<Integer> soHealth;
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
        soType = null;
        soHealth = null;
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
                    soType = jack.rpType();
                    soHealth = jack.rpHealth();
                }
                else{
                    soType = new LinkedList<String>();
                    soHealth = new LinkedList<Integer>();
                    soType.add(jack.info());
                    soHealth.add(jack.health());
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
        graphix.setStroke(new BasicStroke(2.0f));
        //testdata
        soType = new LinkedList<String>();
        soType.add("Unicorn");soType.add("Unicorn");soType.add("Unicorn");soType.add("Unicorn");soType.add("Unicorn");
        soType.add("Unicorn");soType.add("Unicorn");soType.add("Unicorn");soType.add("Unicorn");soType.add("Unicorn");
        soType.add("Unicorn");soType.add("Unicorn");soType.add("Unicorn");soType.add("Unicorn");soType.add("Unicorn");
        soHealth = new LinkedList<Integer>();
        soHealth.add(76);soHealth.add(76);soHealth.add(76);soHealth.add(76);soHealth.add(76);soHealth.add(76);
        soHealth.add(85);soHealth.add(85);soHealth.add(85);soHealth.add(85);soHealth.add(85);soHealth.add(85);
        soHealth.add(100);soHealth.add(100);soHealth.add(100);

        if(soType!=null){
            if(soStats != null){
                graphix.setColor(Color.GREEN);
                graphix.drawImage(graphicsTable.getGraphic(soType.get(0)), 325, 100, null);
                graphix.fillRect(325, 100+miniAvHeight, (miniAvWidth*soHealth.get(0))/100,
                                 healthBarHeight);
                graphix.setColor(Color.MAGENTA);
                graphix.drawRect(325, 100, miniAvWidth, miniAvHeight+healthBarHeight);
            }
            else{
                for(int g = 0; g < soType.size();++g){
                    if( g < 13 ){
                        graphix.setColor(Color.GREEN);
                        graphix.drawImage(graphicsTable.getGraphic(soType.get(g)), 325+(g*(miniAvWidth+4)),
                                                                100, null);
                        graphix.fillRect(325+(g*(miniAvWidth+4)), 100+miniAvHeight, (miniAvWidth*soHealth.get(0))/100,
                                     healthBarHeight);
                        graphix.setColor(Color.MAGENTA);
                        graphix.drawRect(325+(g*(miniAvWidth+4)), 100, miniAvWidth, miniAvHeight+healthBarHeight);
                    }
                    else{
                        graphix.setColor(Color.GREEN);
                        graphix.drawImage(graphicsTable.getGraphic(soType.get(g)), 325+((g-13)*(miniAvWidth+4)),
                                                                100+shift, null);
                        graphix.fillRect( 325+((g-13)*(miniAvWidth+4)), 100+miniAvHeight+shift, (miniAvWidth*soHealth.get(g))/100,
                                     healthBarHeight);
                        graphix.setColor(Color.MAGENTA);
                        graphix.drawRect( 325+((g-13)*(miniAvWidth+4)), 100+shift, miniAvWidth, miniAvHeight+healthBarHeight);
                    }
                }

            }
        }

    }

}
