/*
 * file: OneShotItem.java
 */

package src.model.instances;

import src.model.interfaces.GameTile;
import src.model.interfaces.ItemVisitor;
import src.model.interfaces.ItemEffect;
import src.model.interfaces.InstanceVisitor;
import src.model.ItemManager;

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

    private boolean triggered;

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
        triggered = false;
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
        if(!triggered)
        {
            triggered = true;
            use(thing);
        }
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
    private void use(Instance trigger)
    {
        if(effect.areaEffect())
        {
            this.setInfluenceRadius(effect.radius());

            IsRallyPointVisitor isrv = new IsRallyPointVisitor();

            for(Instance i : withinRadius)
            {
                i.accept(isrv);

                if(!isrv.isRallyPoint())
                    effect.apply(i, location());
            }
        }
        else
        {
            effect.apply(trigger, location());
        }

        ItemManager.getInstance().removeItem(this);

        this.destroy();
    }

    private class IsRallyPointVisitor implements InstanceVisitor
    {
        private boolean isRallyPoint;

        public IsRallyPointVisitor()
        {
            isRallyPoint = false;
        }

        public void visitRallyPoint(RallyPoint rp)
        {
            isRallyPoint = true;
        }

        public void visitUnit(Unit u)
        {
            isRallyPoint = false;
        }

        public void visitStructure(Structure s)
        {
            isRallyPoint= false;
        }

        public boolean isRallyPoint()
        {
            boolean wasRallyPoint = isRallyPoint;
            isRallyPoint = false;
            return wasRallyPoint;
        }
    }
}
