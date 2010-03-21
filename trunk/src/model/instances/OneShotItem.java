/*
 * file: OneShotItem.java
 */

package src.model.instances;

import src.model.interfaces.GameTile;
import src.model.interfaces.ItemVisitor;

/**
 * Represents an item that has an effect and is destroyed after being used.
 *
 * @author Christopher Dudley
 */
public abstract class OneShotItem extends Item
{
    /**
     * Creates a new one-shot item of the specified type at the specified
     * location.
     *
     * @param type the type of the item.
     * @param location the location of the item.
     */
    public OneShotItem(String type, GameTile location)
    {
        super(type, location);
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
}
