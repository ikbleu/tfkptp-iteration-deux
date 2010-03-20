/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package src.view;

import src.model.interfaces.ViewVisitor;
import src.model.interfaces.vGroup;
import src.model.interfaces.vType;
import src.model.interfaces.vInstance;
import src.model.interfaces.vCommand;
import src.model.interfaces.vArgument;

import src.model.interfaces.vUnit;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 *
 * @author spock
 */
 class JackTheViewVisitor implements ViewVisitor{
    private String info = null;
    private String infoType = null;

    //instance specific things
    private Map<String, Integer> stats;
    private String instanceType = null;
    private int workers = -1;
    private int id = -1;
    private int health = -1;
    private List<vUnit> rallyPoint;
    private Map<String, Integer> rpTypeAndHealth;



    public void visitGroup( vGroup v ){
        infoType = "Group";
        info = v.token();
    }

    public void visitType( vType v ){
        infoType = "Type";
        info = v.token();
    }

    public void visitInstance( vInstance v ){
        infoType = "Instance";
        JillTheInstanceVisitor  jill= new JillTheInstanceVisitor();
        v.accept(jill);
        info = jill.instanceType();
        stats = jill.stats();
        workers = jill.workers();
        id = jill.id();
        health = jill.health();
        if(info.equals("Rally Point")){
            rpTypeAndHealth = new HashMap<String, Integer>();
            for(int i = 0; i < rallyPoint.size(); ++i){
                rpTypeAndHealth.put((rallyPoint.get(i)).token(),
                                    (rallyPoint.get(i)).health());
            }
        }
        
    }

    public void visitCommand( vCommand v ){
        infoType = "Command";
        info = v.token();
    }
    
    public void visitArgument( vArgument v ){
        infoType = "Argument";
        info = v.token();
    }

    String infoType(){
        return infoType;
    }

    String info(){
        return info;
    }



}
