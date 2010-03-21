/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package src.view;

import src.model.interfaces.Displayable;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Color;

/**
 *
 * @author spock
 */
 class CommandSelection extends BaseImage{
    private String groupBox = null;
    private String specialBox = null;
    private String typeBox = null;
    private String commandBox = null;
    private String instanceBox = null;
    private String context = null;
    private int BoxWidth = 200;
    private int BoxHeight = 65;
    private int WordIndentW = 15;
    private int WordIndentH = 47;
    private int boxes = 0;
    private int boxNumber = 0;
    private Graphics2D graphix;

    CommandSelection(Displayable[] commands){
        groupBox = null;
        specialBox = null;
        typeBox = null;
        commandBox = null;
        instanceBox = null;
        //normally starts at 0 but eidted for testing
        boxes = 3;
        for(int i = 0;commands != null && i < commands.length;++i){
            JackTheViewVisitor jack = new JackTheViewVisitor();
            commands[i].accept(jack);
            context = jack.infoType();
            if(context.equals("Group")){
                groupBox = jack.info();
                ++boxes;
            }
            else if(context.equals("Instance") && typeBox == null){
                specialBox = jack.info();
                ++boxes;
            }
            else if(context.equals("Type")){
                typeBox = jack.info();
                ++boxes;
            }
            else if(context.equals("Instance")){
                instanceBox = jack.info() + " "  + jack.id();
                ++boxes;
            }
            else if(context.equals("Command")){
                commandBox = jack.info();
                ++boxes;
            }
        }
        refreshImage();
    }

    void setGroupBox(String group){
        groupBox = group;
    }

    void setSpecialBox(String special){
        specialBox = special;
    }

    void setTypeBox(String type){
        typeBox = type;
    }

    void setCommandBox(String comm){
        commandBox = comm;
    }

    void setInstanceBox(String instance){
        instanceBox = instance;
    }

    void refreshImage(){
        if(boxes == 0){
            boxes = 1;
        }

        imageBuffer = new BufferedImage( BoxWidth, BoxHeight * boxes,
                                        BufferedImage.TYPE_INT_ARGB );


        graphix = imageBuffer.createGraphics();

        graphix.setRenderingHint( RenderingHints.KEY_ANTIALIASING,
                                  RenderingHints.VALUE_ANTIALIAS_ON );

        // background image
        graphix.setStroke( new BasicStroke( 4.0f ) );

        graphix.setColor( Color.RED );
        Font f1 = new Font( "Times Roman", Font.BOLD, 22 );
        graphix.setFont( f1 );

        /*
         * Testing code upcoming
         *
         *
         */
        groupBox = "Unit";
        typeBox = "Unicorn";
        instanceBox = "Unicorn 3";

        boxNumber = 0;
        if(groupBox!=null){
            graphix.setColor( Color.MAGENTA );
            //graphix.fillRect( 0, BoxHeight * boxNumber, BoxWidth, BoxHeight * (boxNumber+1));
            //graphix.setColor( Color.PINK );
            graphix.drawImage( graphicsTable.getGraphic("Command"), 0, BoxHeight * boxNumber, null);
            graphix.setColor(Color.BLACK);
            graphix.drawRect( 0, BoxHeight * boxNumber, BoxWidth, BoxHeight * (boxNumber+1));
            graphix.drawString( groupBox, WordIndentW, WordIndentH + BoxHeight * boxNumber);
            ++boxNumber;
        }
        if(specialBox!=null){
            graphix.setColor( Color.MAGENTA );
            //graphix.fillRect( 0, BoxHeight * boxNumber, BoxWidth, BoxHeight * (boxNumber+1));
            //graphix.setColor( Color.PINK );
            graphix.drawImage( graphicsTable.getGraphic("Command"), 0, BoxHeight * boxNumber, null);
            graphix.setColor(Color.BLACK);
            graphix.drawRect( 0, BoxHeight * boxNumber, BoxWidth, BoxHeight * (boxNumber+1));
            graphix.drawString( specialBox, WordIndentW, WordIndentH + BoxHeight * boxNumber);
            ++boxNumber;
        }
        if(typeBox!=null){
            graphix.setColor( Color.MAGENTA );
            //graphix.fillRect( 0, BoxHeight * boxNumber, BoxWidth, BoxHeight * (boxNumber+1));
            //graphix.setColor( Color.PINK );
            graphix.drawImage( graphicsTable.getGraphic("Command"), 0, BoxHeight * boxNumber, null);
            graphix.setColor(Color.BLACK);
            graphix.drawRect( 0, BoxHeight * boxNumber, BoxWidth, BoxHeight * (boxNumber+1));
            graphix.drawString( typeBox, WordIndentW, WordIndentH + BoxHeight * boxNumber);
            ++boxNumber;
        }
        if(commandBox!=null){
            graphix.setColor( Color.MAGENTA );
            //graphix.fillRect( 0, BoxHeight * boxNumber, BoxWidth, BoxHeight * (boxNumber+1));
            //graphix.setColor( Color.PINK );
            graphix.drawImage( graphicsTable.getGraphic("Command"), 0, BoxHeight * boxNumber, null);
            graphix.setColor(Color.BLACK);
            graphix.drawRect( 0, BoxHeight * boxNumber, BoxWidth, BoxHeight * (boxNumber+1));
            graphix.drawString( commandBox, WordIndentW, WordIndentH + BoxHeight * boxNumber);
            ++boxNumber;
        }
        if(instanceBox!=null){
            graphix.setColor( Color.MAGENTA );
            //graphix.fillRect( 0, BoxHeight * boxNumber, BoxWidth, BoxHeight * (boxNumber+1));
            //graphix.setColor( Color.PINK );
            graphix.drawImage( graphicsTable.getGraphic("Command"), 0, BoxHeight * boxNumber, null);
            graphix.setColor(Color.BLACK);
            graphix.drawRect( 0, BoxHeight * boxNumber, BoxWidth, BoxHeight * (boxNumber+1));
            graphix.drawString( instanceBox, WordIndentW, WordIndentH + BoxHeight * boxNumber);
            ++boxNumber;
        }
    }

    int boxes(){
        return boxes;
    }

}
