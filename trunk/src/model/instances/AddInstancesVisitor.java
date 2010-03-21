/*
 * file: AddInstancesVisitor.java
 */

package src.model.instances;

import src.model.interfaces.LocatableVisitor;
import src.model.interfaces.Resource;

import java.util.List;

/**
 * Adds only instances to a list given to the visitor at creation.
 *
 * @author Christopher Dudley
 */
public class AddInstancesVisitor implements LocatableVisitor
{
    private List<Instance> zeList;

    public AddInstancesVisitor(List<Instance> zeList)
    {
        this.zeList = zeList;
    }

    public void visitInstance( Instance i )
    {
        zeList.add(i);
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
