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
import java.awt.Font;

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
    private int statShift = 22;
    private LinkedList<String> soType;
    private LinkedList<Integer> soHealth;
    private Map<String, Integer> soStats;
    private Font f1 = new Font( "Times Roman", Font.BOLD, 17 );
    private Font f2 = new Font( "Times Roman", Font.BOLD, 25 );


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
        graphix.setFont(f1);
        //testdata
        soType = new LinkedList<String>();
        soType.add("Dolphin");soType.add("Unicorn");soType.add("Unicorn");soType.add("Unicorn");soType.add("Unicorn");
        soType.add("Unicorn");soType.add("Unicorn");soType.add("Unicorn");soType.add("Unicorn");soType.add("Unicorn");
        soType.add("Unicorn");soType.add("Unicorn");soType.add("Unicorn");soType.add("Unicorn");soType.add("Unicorn");
        soHealth = new LinkedList<Integer>();
        soHealth.add(76);soHealth.add(76);soHealth.add(76);soHealth.add(76);soHealth.add(76);soHealth.add(76);
        soHealth.add(85);soHealth.add(85);soHealth.add(85);soHealth.add(85);soHealth.add(85);soHealth.add(85);
        soHealth.add(100);soHealth.add(100);soHealth.add(100);
        //more testing
        soStats = new HashMap<String, Integer>();
        soStats.put("Attack", 88);
        soStats.put("Defense", 76);
        soStats.put("Armor", 140);
        soStats.put("Speed", 2);

        if(soType!=null){
            if(soStats != null){
                graphix.setColor(Color.GREEN);
                graphix.drawImage(graphicsTable.getGraphic(soType.get(0)), 325, 100, null);
                graphix.fillRect(325, 100+miniAvHeight, (miniAvWidth*soHealth.get(0))/100,
                                 healthBarHeight);
                graphix.setColor(Color.MAGENTA);
                graphix.drawRect(325, 100, miniAvWidth, miniAvHeight+healthBarHeight);

                graphix.setColor(Color.PINK);
                graphix.setFont(f2);
                graphix.drawString("Instance Stats:", 1000, 100);
                graphix.setFont(f1);
                graphix.drawString("Attack: "+soStats.get("Attack"), 1000, 130);
                graphix.drawString("Defense: "+soStats.get("Defense"), 1000, 130+statShift*1);
                graphix.drawString("Armor: "+soStats.get("Armor"), 1000, 130+statShift*2);
                graphix.drawString("Speed: "+soStats.get("Speed"), 1000, 130+statShift*3);
                graphix.drawImage(graphicsTable.getGraphic(soType.get(0)+"B"), 825, 83, null);
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
