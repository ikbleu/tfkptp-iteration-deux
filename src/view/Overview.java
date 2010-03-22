/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package src.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;

import src.control.interfaces.DisplayableBinding;
import src.model.interfaces.Displayable;

/**
 *
 * @author spock
 */
 class Overview extends BaseImage{
    String title;
    String[] dir;
    String[] instance;
    int selected;
    String subTitle;
    private Graphics2D graphix;
    private Font f1 = new Font( "Times Roman", Font.BOLD, 37 );
    private Font f2 = new Font( "Times Roman", Font.BOLD, 24 );

    Overview(int wid, int hei){
    	
    	title = "TestTitle";
    	subTitle = "TestSub";
    	selected = 1;

        imageWidth = 604;
        imageHeight = 517;
        imageBuffer = new BufferedImage( imageWidth, imageHeight,
                                        BufferedImage.TYPE_INT_ARGB );
        graphix = imageBuffer.createGraphics();
        refreshImage();
    }

    void setOverview(String code, Displayable[] directory, Displayable[] element, Displayable selected) {
    	
    	
    	title = "Structure Overview";
    	JackTheViewVisitor jack = new JackTheViewVisitor();
    	
    	for(int i=0; i<directory.length; i++) {
    		if(directory[i]!=null) {
	    		directory[i].accept(jack);
	    		dir = new String[directory.length];
	    		dir[i] = jack.info() + jack.id();
    		}
    	}
    	
    	for(int i=0; i<element.length; i++) {
    		if(element[i]!=null) {
	    		element[i].accept(jack);
	    		instance = new String[element.length];
	    		instance[i] = jack.info() + jack.id();
    		
	    		if(element[i]==selected)
	    			this.selected = i;
    		}
    	}
    	
    	if(code == "SO")
    		title = "Structure Overview";
    	else if(code == "UO")
    		title = "Unit Overview";
    	else
    		title = "Technology Tree";
    	
    }

    public void refreshImage(){
    	

        graphix.drawImage(graphicsTable.getGraphic("Overview"), 0, 0, null);
        
        graphix.setFont(f1);
        if(title!=null)
        	graphix.drawString(title, 130, 100);
        
        graphix.setFont(f2);
        if(dir!=null) {
        	for(int i=0; i<dir.length; i++) {
        		graphix.drawString(dir[i], 60 + 20*i, 150 + 28*i);
        	}
        
        	int j=Math.max(0,selected-4);
        	if(instance!=null) {
        		for(int i=0; i<8; i++) {
	        		if(selected==j)
	        			graphix.setColor(Color.PINK);
	        		graphix.drawString(instance[j], 90 + 20*dir.length, 210 + 28*i);
	        		graphix.setColor(Color.WHITE);
	        		if(++j>=instance.length)
	        			break;
        		}
        	}
        }
        
        if(subTitle!=null)
        	graphix.drawString(subTitle, 60, 450);
    }


}
