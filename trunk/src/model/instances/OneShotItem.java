/*
 * file: OneShotItem.java
 */

package src.model.instances;

import src.model.interfaces.GameTile;
import src.model.interfaces.ItemVisitor;
import src.model.interfaces.ItemEffect;

import java.util.List;
import java.util.ArrayList;

/**
 * Represents an item that has an effect and is destroyed after being used.
 *
 * @author Christopher Dudley
 */
public class OneShotItem extends Item
{
    private List<Instance> withinRadius;

    private ItemEffect effect;

    /**
     * Creates a new one-shot item of the specified type at the specified
     * location.
     *
     * @param type the type of the item.
     * @param location the location of the item.
     */
    public OneShotItem(String type, GameTile location, ItemEffect effect)
    {
        super(type, location);
        withinRadius = new ArrayList<Instance>();
        this.effect = effect;
    }

    /**
     * One-shot items do not block tiles, so this always returns false.
     *
     * @return false
     */
    public boolean blocksTile()
    {
        return false;
    }

    /**
     * Accepts an item visitor and tells it to execute its one-shot item
     * specific logic.
     *
     * @param iv the item visitor.
     */
    public void accept(ItemVisitor iv)
    {
        iv.visitOneShot(this);
    }

    public void entered(Instance thing)
    {
        withinRadius.add(thing);
    }

    public void exited(Instance thing)
    {
        withinRadius.remove(thing);
    }

    /**
     * Causes the item to apply its affect to the instances within its
     * radius.
     *
     * @param trigger the instance that triggered the item's use.
     */
    public void use(Instance trigger)
    {
        if(effect.areaEffect())
        {
            this.setInfluenceRadius(effect.radius());

            for(Instance i : withinRadius)
                effect.apply(i, location());
        }
        else
        {
            effect.apply(trigger, location());
        }
    }
}
