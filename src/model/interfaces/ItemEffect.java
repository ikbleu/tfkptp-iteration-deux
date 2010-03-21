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
     */
    public void apply(Instance i);

    /**
     * Returns whether or not the item has an area effect or only affects the
     * thing that triggered it.
     *
     * @return whether the item has an area effect (true) or single-target effect (false)
     */
    public boolean areaEffect();
}
