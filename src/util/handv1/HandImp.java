package src.util.handv1;

import src.util.Hand;
import src.util.Lens;
import src.util.HasComparable;
import java.util.TreeMap;

/**
 *
 * @author kagioglu
 */
class HandImp<T extends HasComparable> implements Hand<T> {
    private final Object lock;
    private final TreeMap<Comparable,T> data;
    HandImp(Class<T> clazz) {
        this.lock = new Object();
        this.data = new TreeMap();
    }
    public T add(T item) {
        synchronized(this.lock) {
            Comparable key = item.comparable();
            if(key == null) { throw new NullPointerException(); }
            else { return this.data.put(key, item); }
        }
    }
    public T remove(T item) {
        synchronized(this.lock) {
            Comparable key = item.comparable();
            if(key == null) { throw new NullPointerException(); }
            else { return this.data.remove(key); }
        }
    }
    public Lens<T> spawnLens() {
        synchronized(this.lock) {
            return new LensImp<T>(this.lock, this.data);
        }
    }
    public void clear() { synchronized(this.lock) { this.data.clear(); } }
}
