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




    public void visitGroup( vGroup v ){

    }

    public void visitType( vType v ){
        group = v.token();
    }

    public void visitInstance( vInstance v ){

    }

    public void visitCommand( vCommand v ){

    }
    
    public void visitArgument( vArgument v ){

    }
}
