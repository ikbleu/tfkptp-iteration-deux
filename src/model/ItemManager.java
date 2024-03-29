/*
 * file: ItemManager.java
 */

package src.model;

import java.util.Map;
import java.util.HashMap;
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
import src.model.instances.itemeffects.Soma;

import java.util.Random;
import src.model.enums.Direction;

import src.util.RandomChooser;

/**
 * Creates and manages items on the game map, as well as distributing ticks
 * to items.
 *
 * @author Christopher Dudley
 */
public class ItemManager implements ItemVisibilityHolder
{
    // Gee, I wonder what this is.
    private Map<GameTile, Item> itemList;

    private RandomChooser<ItemEffect> effectChooser;

    private Random rand;

    private ItemManager()
    {
        itemList = new HashMap<GameTile, Item>();

        rand = new Random(System.currentTimeMillis());
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
        for(Item i : itemList.values())
            removeItem(i);

        GameTile center = theMap.getOrigin();
        int radius = theMap.getMapRadius();

        List<GameTile> allTiles = center.getTilesAround(radius);
        System.out.println("numTiles: " + allTiles.size());

        effectChooser = new RandomChooser<ItemEffect>();
        effectChooser.add(new Bomb(rand.nextInt(15) + 1, 1, 0.5), 200);
        effectChooser.add(new Soma(15, 1, 0.5), 200);
        effectChooser.add(new Soma(50, 2, 0.5), 50);
        effectChooser.add(new Bomb(rand.nextInt(25) + 25, 2, 0.5), 50);

        // B***H I'VE GOT A BOMB!
        effectChooser.add(new Bomb(Integer.MAX_VALUE, theMap.getDiameter(), 0), 1);

        GameTile start1 = theMap.getStartingLocation1();
        GameTile start2 = theMap.getStartingLocation2();


        for(GameTile gt : allTiles)
        {
            boolean makeItem = false;
            int randNum = rand.nextInt(10);
            if(randNum > 6)
                makeItem = true;

            if(gt.getDistanceFrom(start1) > 2 && gt.getDistanceFrom(start2) > 2 &&
                    makeItem)
            {
                makeNewItem(gt);
            }
        }
    }

    private void makeNewItem(GameTile location)
    {
        int option = rand.nextInt(100);

        if(option >= 0 && option < 70)
            makeNewOneShot(location);
        else
        {
            makeNewObstacle(location);
        }
    }

    private void makeNewOneShot(GameTile location)
    {
        ItemEffect effect = effectChooser.get();

        itemList.put(location, new OneShotItem(effect.type(), location, effect));
    }

    private void makeNewObstacle(GameTile location)
    {
        itemList.put(location, new Obstacle("itemObstacle", location));
    }

    public List<Item> getAllItems()
    {
        ArrayList<Item> zeList = new ArrayList<Item>();
        zeList.addAll(itemList.values());
        return Collections.unmodifiableList(zeList);
    }

    public Item getItemAt(GameTile location)
    {
        return itemList.get(location);
    }

    public void removeItem(Item it)
    {
            itemList.remove(it.location());
            it.destroy();
    }
}