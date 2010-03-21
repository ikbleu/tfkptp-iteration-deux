/*
 * file: ItemEffect.java
 */

package src.model.interfaces;

import src.model.instances.Instance;

/**
 * Defines an effect from an item that can be applied to an instance.
 *
 * @author Christopher Dudley
 */
public interface ItemEffect
{
    /**
     * Applies the items effect to the specified instance.
     *
     * @param i the instance to apply the effect to.
     * @param source the tile the effect stems from.
     */
    public void apply(Instance i, GameTile source);

    /**
     * Returns whether or not the item has an area effect or only affects the
     * thing that triggered it.
     *
     * @return whether the item has an area effect (true) or single-target effect (false)
     */
    public boolean areaEffect();

    /**
     * For area of effect item effects, returns the radius of tiles that the
     * effect affects.
     *
     * @return the radius of the effect if it has one, -1 if not.
     */
    public int radius();
}
