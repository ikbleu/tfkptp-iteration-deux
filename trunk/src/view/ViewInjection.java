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
public interface ViewInjection {
    public abstract void injectionFairyLily(String code,
                                            Displayable[] direct,
                                            Displayable[] dList,
                                            Displayable selection);
}
