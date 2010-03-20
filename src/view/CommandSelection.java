/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package src.view;

import src.model.interfaces.Displayable;

import java.awt.image.BufferedImage;

/**
 *
 * @author spock
 */
 class CommandSelection extends HasAnImage{
    private String groupBox = null;
    private String specialBox = null;
    private String typeBox = null;
    private String commandBox = null;
    private String instanceBox = null;
    private String context = null;
    private int BoxWidth = 200;
    private int BoxHeight = 65;
    private int WordIndent = 15;
    private int boxes = 0;
    private int boxNumber = 0;

    CommandSelection(Displayable[] commands){
        groupBox = null;
        specialBox = null;
        typeBox = null;
        commandBox = null;
        instanceBox = null;
        boxes = 0;
        for(int i = 0; i < commands.length;++i){
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
                instanceBox = jack.info();
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
        imageBuffer = new BufferedImage( BoxWidth, BoxHeight*boxes,
                                        BufferedImage.TYPE_INT_ARGB );
        boxNumber = 0;
        if(groupBox!=null){

        }
    }

}
