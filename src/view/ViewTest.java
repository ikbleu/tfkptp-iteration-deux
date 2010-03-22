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
public class ViewTest {
    public static void main(String[] args) {
        View viewTest = new View();
        Displayable[] dt = new Displayable[1];
        viewTest.injectionFairyLily("TT", dt, dt, dt[0]);
    }
}
