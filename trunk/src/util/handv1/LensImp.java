package src.util.handv1;

import src.util.Lens;
import src.util.HasComparable;
import src.util.DataVisitor;
import java.util.TreeMap;
import java.util.Map;
import java.util.Collection;
/**
 *
 * @author kagioglu
 */
class LensImp<T extends HasComparable> implements Lens<T> {
    private final Object lock;
    private final TreeMap<Comparable,T> data;
    private Comparable key;
    LensImp(Object lock, TreeMap<Comparable,T> data) {
        this.lock = lock;
        this.data = data;
        if(this.data.isEmpty()) { this.key = null; }
        else { this.key = this.data.firstKey(); }
    }
    public void fill(Collection<T> coll) {
        synchronized(this.lock) {
            for(T value : this.data.values()) { coll.add(value); }
        }
    }
    public void next(DataVisitor<T> visitor) {
        synchronized(this.lock) {
            if(this.data.isEmpty()) { this.key = null; visitor.missing(); }
            else {
                Map.Entry<Comparable,T> entry = null;
                if(this.key == null) { entry = this.data.firstEntry(); }
                else {
                    entry = this.data.higherEntry(this.key);
                    if(entry == null) { entry = this.data.firstEntry(); }
                }
                this.key = entry.getKey();
                visitor.found(entry.getValue());
            }
        }
    }
    public void prev(DataVisitor<T> visitor) {
        synchronized(this.lock) {
            if(this.data.isEmpty()) { this.key = null; visitor.missing(); }
            else {
                Map.Entry<Comparable,T> entry = null;
                if(this.key == null) { entry = this.data.lastEntry(); }
                else {
                    entry = this.data.lowerEntry(this.key);
                    if(entry == null) { entry = this.data.lastEntry(); }
                }
                this.key = entry.getKey();
                visitor.found(entry.getValue());
            }
        }
    }
    public void pick(T item, DataVisitor<T> visitor) {
        synchronized(this.lock) {
            this.key = item.comparable();
            if(this.key == null) { throw new NullPointerException(); }
            else {
                if(this.data.containsKey(this.key)) {
                    visitor.found(this.data.get(this.key));
                }
                else { this.key = null; visitor.missing(); }
            }
        }
    }
    public void accept(DataVisitor<T> visitor) {
        synchronized(this.lock) {
            if(this.key == null) { visitor.missing(); }
            else {
                if(this.data.containsKey(this.key)) {
                    visitor.found(this.data.get(this.key));
                }
                else { this.key = null; visitor.missing(); }
            }
        }
    }
}
