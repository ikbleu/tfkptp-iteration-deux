/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package src.view;

import src.model.interfaces.Displayable;

/**
 *
 * @author spock
 */
 class CommandSelection extends HasAnImage{
    private String groupBox;
    private String specialBox;
    private String typeBox;
    private String commandBox;
    private String instanceBox;

    CommandSelection(Displayable[] commands){
        for(int i = 0; i < commands.length;++i){
            JackTheViewVisitor jack = new JackTheViewVisitor();
            commands[i].accept(jack);

        }
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

    }

}
