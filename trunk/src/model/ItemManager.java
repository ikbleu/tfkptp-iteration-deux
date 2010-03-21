/*
 * file: ItemManager.java
 */

package src.model;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import src.model.interfaces.ItemVisibilityHolder;
import src.model.instances.Item;
import src.model.interfaces.GameTile;

import src.model.instances.Obstacle;
import src.model.instances.OneShotItem;
import src.model.interfaces.ItemEffect;
import src.model.instances.itemeffects.Bomb;

import java.util.Random;

/**
 * Creates and manages items on the game map, as well as distributing ticks
 * to items.
 *
 * @author Christopher Dudley
 */
public class ItemManager implements ItemVisibilityHolder
{
    // Gee, I wonder what this is.
    private List<Item> itemList;

    private ItemManager()
    {
        itemList = new ArrayList<Item>();
    }

    private static class ItemManagerHolder
    {
        static final ItemManager INSTANCE = new ItemManager();
    }

    public static ItemManager getInstance()
    {
        return ItemManagerHolder.INSTANCE;
    }

    public void setMap(GameMap theMap)
    {
        GameTile center = theMap.getOrigin();
        int radius = theMap.getMapRadius();

        List<GameTile> allTiles = center.getTilesAround(radius);

        Random rand = new Random(System.currentTimeMillis());

        for(GameTile gt : allTiles)
        {
            if(gt != theMap.getStartingLocation1() && gt != theMap.getStartingLocation2() && rand.nextInt(10) > 7)
                makeNewItem(gt);
        }
    }

    private void makeNewItem(GameTile location)
    {
        Random rand = new Random(System.currentTimeMillis());

        int option = rand.nextInt(100);

        if(option >= 0 && option < 70)
            makeNewOneShot(location);
        else
            makeNewObstacle(location);
    }

    private void makeNewOneShot(GameTile location)
    {
        Random rand = new Random(System.currentTimeMillis());

        ItemEffect effect = new Bomb(0, 0, 0);

        int option = rand.nextInt(1000);

        if(option >= 0 && option < 800)
        {
            // Small bomb
            effect = new Bomb(rand.nextInt(15) + 1, 1, 0.5);
        }
        if(option >= 800 && option < 999)
        {
            // Large bomb
            effect = new Bomb(rand.nextInt(50) + 1, 2, 0.5);
        }
        if(option == 999)
        {
            // Friggin nuke! EVERYTHING DIES.
            effect = new Bomb(Integer.MAX_VALUE, 30, 0);
        }

        itemList.add(new OneShotItem(effect.type(), location, effect));
    }

    private void makeNewObstacle(GameTile location)
    {
        itemList.add(new Obstacle("itemObstacle", location));
    }

    public List<Item> getAllItems()
    {
        return Collections.unmodifiableList(itemList);
    }

    public void removeItem(Item it)
    {
            itemList.remove(it);
    }
}
