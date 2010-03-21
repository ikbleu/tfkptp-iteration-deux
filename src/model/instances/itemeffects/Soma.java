/*
 * file: Soma.java
 */

package src.model.instances.itemeffects;

import src.model.interfaces.ItemEffect;
import src.model.interfaces.GameTile;

import src.model.instances.Instance;

/**
 * Effect for an item that heals instances within a radius.
 *
 * @author Christopher Dudley
 */
public class Soma implements ItemEffect
{
    private int maxHealing;
    private int radius;
    private double decay;

    /**
     * Creates a new healing item effect.
     *
     * @param maxHealing the maximum healing done by the item.
     * @param radius the radius of the healing.
     * @param decay how quickly the effect decays with radius (percentage).
     */
    public Soma(int maxHealing, int radius, double decay)
    {
        this.maxHealing = maxHealing;
        this.radius = radius;
        this.decay = decay;
    }

    /**
     * Returns the type of the item effect.
     *
     * @return the type of the item effect.
     */
    public String type()
    {
        return "itemHeal";
    }

    /**
     * Returns the radius of the item effect.
     *
     * @return the radius of the item effect.
     */
    public int radius()
    {
        return radius;
    }

    /**
     * This effect is an area of effect, so return true.
     *
     * @return true
     */
    public boolean areaEffect()
    {
        return true;
    }

    /**
     * Applies the effect to the given triggering instance at the specified
     * location.
     *
     * @param trigger the triggering instance.
     * @param location the location of the triggering instance.
     */
    public void apply(Instance trigger, GameTile location)
    {
        GameTile target = trigger.location();
        int distance = location.getDistanceFrom(target);

        int healingDone = (int) (maxHealing * (Math.pow(1 - decay, distance)));
        trigger.heal(healingDone);
    }
}
