/*
 * file: Bomb.java
 */

package src.model.instances.itemeffects;

import src.model.interfaces.ItemEffect;
import src.model.interfaces.GameTile;
import src.model.instances.Instance;

/**
 * Contains the effect for an item that blows up, damaging all units within a
 * certain radius once activated.
 *
 * @author Christopher Dudley
 */
public class Bomb implements ItemEffect
{
    private int maxDamage;
    private int radius;

    private float decay;

    /**
     * Creates a new bomb with a specified maximum damage dealth, a maximum
     * radius of effect, and a percentage rate at which damage decays.
     *
     * @param maxDamage the maximum damage of the bomb.
     * @param radius the radius the bomb effects.
     * @param decay the rate at which the explosion damage decays.
     */
    public Bomb(int maxDamage, int radius, float decay)
    {
        this.maxDamage = maxDamage;
        this.radius = radius;
        this.decay = decay;
    }

    public void apply(Instance i, GameTile source)
    {
        GameTile target = i.location();
        int distance = source.getDistanceFrom(target);

        int damageTaken = Math.max(0, (int) (maxDamage * (1 - distance * decay)));
        i.takeDamage(damageTaken);
    }

    /**
     * The explosion is an area of effect, so returns true.
     *
     * @return true.
     */
    public boolean areaEffect()
    {
        return true;
    }

    /**
     * Returns the radius of the bomb explosion.
     *
     * @return the radius of the explosion.
     */
    public int radius()
    {
        return radius;
    }
}
