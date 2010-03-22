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
    private List<Item> itemList;

    private RandomChooser<ItemEffect> effectChooser;

    private ItemManager()
    {
        itemList = new ArrayList<Item>();

        Random rand = new Random(System.currentTimeMillis());
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
        effectChooser = new RandomChooser<ItemEffect>();
        effectChooser.add(new Bomb(rand.nextInt(15) + 1, 1, 0.5), 200);
        effectChooser.add(new Soma(15, 1, 0.5), 200);
        effectChooser.add(new Soma(50, 2, 0.5), 50);
        effectChooser.add(new Bomb(rand.nextInt(25) + 25, 2, 0.5), 50);

        // B***H I'VE GOT A BOMB!
        effectChooser.add(new Bomb(Integer.MAX_VALUE, theMap.getDiameter(), 0), 1);

        GameTile start1 = theMap.getStartingLocation1();
        GameTile start2 = theMap.getStartingLocation2();

        // TODO: Remove below debugging code.
        GameTile special = start1.getNeighbor(Direction.S);
        ItemEffect specialEffect = new Bomb(15, 1, 0.5);
        itemList.add(new OneShotItem(specialEffect.type(), special, specialEffect));
        // End debug code.

        for(GameTile gt : allTiles)
        {
            if(gt.getDistanceFrom(start1) > 2 && gt.getDistanceFrom(start2) > 2 &&
                    rand.nextInt(10) > 6)
            {
                makeNewItem(gt);
            }
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
        ItemEffect effect = effectChooser.get();

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