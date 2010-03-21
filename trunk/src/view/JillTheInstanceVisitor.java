/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package src.view;

import src.model.instances.RallyPoint;
import src.model.instances.Structure;
import src.model.instances.Unit;
import src.model.interfaces.InstanceVisitor;
import src.model.interfaces.vInstanceVisitor;
import src.model.interfaces.vUnit;
import src.model.interfaces.vStructure;
import src.model.interfaces.vRallyPoint;

import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;

/**
 *
 * @author spock
 */
 class JillTheInstanceVisitor implements vInstanceVisitor{
 String instanceType = null;
 int workers = -1;
 int id = -1;
 int health = -1;
 Map<String, Integer> stats;
 List<vUnit> rallypoint;
    

    public void visitUnit( vUnit u ){
        instanceType = u.token();
        stats = new HashMap<String, Integer>();
        u.stats(stats);
        id = u.id();
        health = u.health();
    }

    public void visitStructure( vStructure s ){
        instanceType = s.token();
        stats = new HashMap<String, Integer>();
        s.stats(stats);
        id = s.id();
        health = s.health();
        workers = s.workers();
    }

    public void visitRallyPoint( vRallyPoint r ){
        instanceType = r.token();
        workers = r.workers();
        rallypoint = new LinkedList<vUnit>();
        r.units(rallypoint);
    }

    String instanceType(){
        return instanceType;
    }

    int workers(){
        return workers;
    }

    int id(){
        return id;
    }

    int health(){
        return health;
    }

    Map<String, Integer> stats(){
        return stats;
    }
}
