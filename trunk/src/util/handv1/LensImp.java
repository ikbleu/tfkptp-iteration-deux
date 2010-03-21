package src.util.handv1;

import src.util.Lens;
import src.util.OrderingFactory;
import src.util.Iter;
import java.util.Set;

/**
 *
 * @author kagioglu
 */
class LensImp<T> implements Lens<T> {
    private Object lock;
    private Set<T> data;
    LensImp(Set<T> set) { this.data = set; }
    public Iter<T> iter(OrderingFactory<T> factory) {
        return new IterImp<T>(this.lock, this.data, factory);
    }
}
