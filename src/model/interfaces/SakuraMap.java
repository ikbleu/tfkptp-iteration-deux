/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package src.model.interfaces;

import java.util.Map;

import src.view.MapBuilder;

/**
 *
 * @author spock
 */
public interface SakuraMap {
    public int mapWidth();
    public int mapHeight();
    public void build(MapBuilder[][] b);
    public Map<String, Integer> getPlayerResources();
}
