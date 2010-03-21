/*
 * file: AddInstancesVisitor.java
 */

package src.model.instances;

import src.model.interfaces.LocatableVisitor;
import src.model.interfaces.Resource;

import java.util.List;

/**
 * Removes only instances from a list given to the visitor at creation.
 *
 * @author Christopher Dudley
 */
public class RemoveInstancesVisitor implements LocatableVisitor
{
    private List<Instance> zeList;

    public RemoveInstancesVisitor(List<Instance> zeList)
    {
        this.zeList = zeList;
    }

    public void visitInstance( Instance i )
    {
        zeList.remove(i);
    }

    public void visitWorkerGroup( WorkerGroup wg)
    {
        //Do nothing!
    }

    public void visitItem(Item it)
    {
        //Do nothing!
    }

    public void visitResource( Resource r)
    {
        //Do nothing!
    }

    public void visitDecal(Decal d)
    {
        //Do nothing!
    }
}
