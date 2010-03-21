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
    private final Object lock;
    private final Set<T> data;
    HandImp(Class<T> clazz) {
        this.lock = new Object();
        this.data = new HashSet();
    }
    public boolean add(T item) { return this.data.add(item); }
    public boolean remove(T item) { return this.data.remove(item); }
    public Lens<T> spawnLens() { return new LensImp<T>(this.lock, this.data); }
}