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

/**
 *
 * @author spock
 */
 class JackTheViewVisitor implements ViewVisitor{
    String group = null;
    String type = null;
    String instance = null;
    String command = null;
    String argument = null;

    //instance specific things
    Map<String, Integer> stats;
    String instanceType = null;
    int workers = -1;
    int id = -1;
    int health = -1;
    List<vUnit> rallypoint;


    public void visitGroup( vGroup v ){
        group = v.token();
    }

    public void visitType( vType v ){
        type = v.token();
    }

    public void visitInstance( vInstance v ){
        JillTheInstanceVisitor  jill= new JillTheInstanceVisitor();
        v.accept(jill);
        //health
        
    }

    public void visitCommand( vCommand v ){
        command = v.token();
    }
    
    public void visitArgument( vArgument v ){
        argument = v.token();
    }

}
