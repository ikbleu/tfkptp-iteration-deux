/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package src.view;

import java.util.HashMap;

import src.model.enums.DecalType;
import src.model.enums.Direction;
import src.model.enums.Visibility;

/**
 *
 * @author spock
 */
public class EnumTableSingleton {

    HashMap< Enum, String > table;

    private EnumTableSingleton(){

        table = new HashMap<Enum, String>();
        table.put( Visibility.VISIBLE, "visible");
        table.put( Visibility.NON_VISIBLE, "non visible");
        table.put( Visibility.SHROUDED, "shrouded");
        table.put( DecalType.NONE, "no decal");
        table.put( DecalType.RED_CROSS, "red cross");
        table.put( DecalType.SKULL_XBONES, "skull xbones");
        table.put( Direction.N, "North");
        table.put( Direction.NE, "North East");
        table.put( Direction.NW, "North West");
        table.put( Direction.S, "South");
        table.put( Direction.SE, "South East");
        table.put( Direction.SW, "South West");

    }

    String getString(Enum key){
        return table.get(key);
    }
    private static class EnumTableSingletonHolder {
        private static final EnumTableSingleton ONEANDONLY =
                                            new EnumTableSingleton();
    }

    public static EnumTableSingleton getInstance() {
        return EnumTableSingletonHolder.ONEANDONLY;
    }
}
