/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package src.model.interfaces;

/**
 *
 * @author spock
 */
public interface Displayable {
    public void accept(ViewVisitor v);
}
