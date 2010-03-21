/*
 * file: ItemVisitor.java
 */

package src.model.interfaces;

import src.model.instances.OneShotItem;
import src.model.instances.Obstacle;

/**
 * Visitor that does specific actions depending on the type of the item being
 * visited.
 *
 * @author Christopher Dudley
 */
public interface ItemVisitor
{
    public void visitOneShot(OneShotItem osi);
    public void visitObstacle(Obstacle o);
}
