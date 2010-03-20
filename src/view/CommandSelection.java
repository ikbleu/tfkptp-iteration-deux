/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package src.view;

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
