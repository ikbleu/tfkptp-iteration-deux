package src.model.interfaces;

import src.model.instances.Instance;
import src.model.instances.WorkerGroup;
import src.model.instances.Item;
import src.model.instances.Decal;

public interface LocatableVisitor {
	public void visitInstance( Instance i );
        public void visitWorkerGroup( WorkerGroup wg);
        public void visitItem(Item it);
        public void visitResource( Resource r);
        public void visitDecal(Decal d);
}
