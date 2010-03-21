package src.util.handv1;

import src.util.Iter;
import src.util.DataVisitor;
import src.util.OrderingFactory;
import src.util.Ordering;
import src.util.DoYouHas;
import java.util.Set;

/**
 *
 * @author kagioglu
 */
public class IterImp<T> implements Iter<T> {
    private final Object lock;
    private final DoYouHas<T> dyh;
    private final Ordering<T> ordering;
    
    private static class Has<T> implements DoYouHas<T> {
        private Set<T> data;
        Has(Set<T> data) { this.data = data; }
        public boolean contains(T item) {
            if(item == null) { throw new RuntimeException("no null allowed!"); }
            else { return this.data.contains(item); }
        }
        public boolean isEmpty() { return this.data.isEmpty(); }
    }
    
    IterImp(Object lock, Set<T> data, OrderingFactory<T> factory) {
        this.lock = lock;
        this.dyh = new Has<T>(data);
        this.ordering = factory.make(this.dyh);
    }

    public void next(DataVisitor<T> visitor) {
        synchronized(this.lock) {
            this.ordering.next();
            this.messageVisitor(visitor);
        }
    }

    public void prev(DataVisitor<T> visitor) {
        synchronized(this.lock) {
            this.ordering.prev();
            this.messageVisitor(visitor);
        }
    }

    public void pick(T item, DataVisitor<T> visitor) {
        synchronized(this.lock) {
            this.ordering.skipTo(item);
            this.messageVisitor(visitor);
        }
    }

    public void accept(DataVisitor<T> visitor) {
        synchronized(this.lock) { this.messageVisitor(visitor); }
    }

    protected void messageVisitor(DataVisitor<T> visitor) {
        if(this.dyh.contains(this.ordering.current())) {
            visitor.found(this.ordering.current());
        }
        else {
            visitor.missing();
        }
    }
}
