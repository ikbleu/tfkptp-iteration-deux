package src.util.handv1;

import src.util.Hand;
import src.util.Lens;
import java.util.Set;
import java.util.HashSet;

/**
 *
 * @author kagioglu
 */
class HandImp<T> implements Hand<T> {
    protected Set<T> set;
    HandImp(Class<T> clazz) {
        this.set = new HashSet();
    }
    public boolean add(T item) { return this.set.add(item); }
    public boolean remove(T item) { return this.set.remove(item); }
    public Lens<T> spawnLens() { throw new RuntimeException("TODO"); }
}
