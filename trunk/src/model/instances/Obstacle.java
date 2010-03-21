/*
 * file: Obstacle.java
 */

package src.model.instances;

import src.model.interfaces.GameTile;
import src.model.interfaces.ItemVisitor;

/**
 * Represents an item that impedes unit movement.
 *
 * @author Christopher Dudley
 */
public class Obstacle extends Item
{
    /**
     * Creates a new obstacle of the specified type at the specified location.
     *
     * @param type the type of the obstacle.
     * @param location the location of the obstacle.
     */
    public Obstacle(String type, GameTile location)
    {
        super(type, location);
    }

    /**
     * All obstacles block the tile they are on, so this always returns true.
     *
     * @return true
     */
    public boolean blocksTile()
    {
        return true;
    }

    /**
     * Accepts an item visitor and asks the visitor to execute logic specific
     * to obstacles.
     *
     * @param iv the item visitor.
     */
    public void accept(ItemVisitor iv)
    {
        iv.visitObstacle(this);
    }

    public void exited(Locatable thing)
    {
        // Obstacles don't care about things near them.
    }

    public void entered(Locatable thing)
    {
        // Obstacles don't care about things near them.
    }
}
