package src.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;


import src.control.Binding;
import src.control.interfaces.DisplayableBinding;
import src.control.interfaces.DisplayableKeyMap;
import src.model.interfaces.Displayable;

/**
 *
 * @author rdshack
 */
 class KBOverview extends BaseImage{
    String title;
    ArrayList<String> context;
    List<List<DisplayableBinding>> keys;
    DisplayableBinding selected;
    
    private Graphics2D graphix;
    private Font f1 = new Font( "Times Roman", Font.BOLD, 37 );
    private Font f2 = new Font( "Times Roman", Font.BOLD, 24 );

    KBOverview(int wid, int hei){
    	
    	context = new ArrayList<String>();
    	title = "Keyboard Control";
    	selected = null;

        imageWidth = 604;
        imageHeight = 517;
        imageBuffer = new BufferedImage( imageWidth, imageHeight,
                                        BufferedImage.TYPE_INT_ARGB );
        graphix = imageBuffer.createGraphics();
        refreshImage();
    }

    void setKBOverview(DisplayableKeyMap dkm) {
    	
    	selected = dkm.selected();
    	int cn = 0;
    	while(dkm.hasNext()) {
    		context.add(dkm.context());
    		keys.add(dkm.getBindingList());
    		dkm.nextContext();
    		cn++;
    	}

    	
    }

    public void refreshImage(){
    	
    	/*context = new ArrayList<String>();
    	context.add("unit overview");
    	context.add("structure overview");
    	context.add("commands");
    	context.add("hot sauce");
    	
    	ArrayList<DisplayableBinding> inner = new ArrayList<DisplayableBinding>();
    	keys.add(inner);

    	*/

        graphix.drawImage(graphicsTable.getGraphic("Overview"), 0, 0, null);
        
        graphix.setFont(f1);
        if(title!=null)
        	graphix.drawString(title, 130, 100);
        
        graphix.setFont(f2);

        int drawPos = 0;
        int listNum = 0;
        for(String con : context) {
        	graphix.drawString(con, 60, 150+28*drawPos++);
        	
        	for(DisplayableBinding db : keys.get(listNum)) {
        		
        		if(db == selected)
        			graphix.setColor(Color.PINK);
        		graphix.drawString( db.meaning(), 80, 150+28);
        		graphix.drawString( db.meaning(), 250, 150+28*drawPos++);
        	}
        	graphix.setColor(Color.WHITE);
        }

    }


}