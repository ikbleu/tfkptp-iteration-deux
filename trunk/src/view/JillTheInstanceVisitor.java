/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package src.view;

import src.model.interfaces.InstanceVisitor;
import src.model.interfaces.vUnit;
import src.model.interfaces.vStructure;
import src.model.interfaces.vRallyPoint;

import java.util.List;
import java.util.Map;

/**
 *
 * @author spock
 */
 class JillTheInstanceVisitor implements InstanceVisitor{
     String type = null;
     int workers = -1;
     int id = -1;
     int health = -1;
     Map<String, Integer> stats;
     List<vUnit> rallypoint;
    

    public void visitUnit( vUnit u ){
        type = u.token();
        stats = u.stats();
        id = u.id();
        health = u.health();
    }

    public void visitStructure( vStructure s ){
        type = s.token();
        stats = s.stats();
        id = s.id();
        health = s.health();
        workers = s.workers();
    }

    public void visitRallyPoint( vRallyPoint r ){
        type = r.token();
        workers = r.workers();
        rallypoint = r.units();
    }

}
