/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package src.view;

import src.model.interfaces.TypeCode;

/**
 *
 * @author spock
 */
public interface BuilderOfDisplayable {

    public abstract void setID(int id);
    public abstract void setName(String name);
    public abstract void setType(TypeCode type);
    public abstract void setHealth(int health);
    public abstract void setStats(StatsBuilder statsBuilder);

}
