/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package src.view;

import src.control.interfaces.DisplayableBinding;
import src.control.interfaces.DisplayableKeyMap;
import src.control.interfaces.KBinjection;
import src.model.interfaces.Displayable;
import src.model.interfaces.SakuraMap;

import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.List;


/**
 *
 * @author spock
 */
public class View implements ViewInjection, KBinjection{
    private ScreenManager screenManager;
    private SakuraMap sakuraMap;
    private HashMap<String,ViewInjectionElement> injections;
    private boolean ready;

    public View(SakuraMap sakura){
    	GraphicsTableSingleton.getInstance();

        screenManager = new ScreenManager(sakura.mapWidth(), sakura.mapHeight(), sakura);
        screenManager.start();
        
        injections = new HashMap<String,ViewInjectionElement>();
        injections.put("HUD", new HudInjection());
        injections.put("SO", new OverviewInjection());
        injections.put("TT", new TechTreeInjection());
        injections.put("KB", new KeyBindingInjection());
    }

    public void injectionFairyLily(String code,
                                            Displayable[] direct,
                                            Displayable[] dList,
                                            Displayable selection){
    	
    	ViewInjectionElement vie = injections.get(code);
    	vie.injectionEffect(screenManager, code, direct, dList, selection);
    	System.out.println("About to inject!");
    }

    public void injectionCloseMenu(){
        
    }

    public void toggleViewPort(){
        screenManager.toggleViewPort();
    }

    public void register(SakuraMap sakura, KeyListener control){
        sakuraMap = sakura;
        screenManager.addKeyListener(control);
    }

	//KBinjection
	public void displayControls(DisplayableKeyMap dkm) {
		
		screenManager.updateKBOverview(dkm);
	}

}
