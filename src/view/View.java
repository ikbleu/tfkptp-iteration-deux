/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package src.view;

import src.model.interfaces.Displayable;
import src.model.interfaces.SakuraMap;

import java.awt.event.KeyListener;


/**
 *
 * @author spock
 */
public class View implements ViewInjection{
    private ScreenManager screenManager;
    private SakuraMap sakuraMap;

    public View(){
        screenManager = new ScreenManager(15, 15);
    }

    public void injectionFairyLily(String code,
                                            Displayable[] direct,
                                            Displayable[] dList,
                                            Displayable selection){
    }

    public void injectionCloseMenu(){
        
    }

    public void register(SakuraMap sakura, KeyListener control){
        sakuraMap = sakura;
        //KeyListener statement
    }

}
