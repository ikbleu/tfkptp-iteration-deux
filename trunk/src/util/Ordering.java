package src.util;

/**
 *
 * @author kagioglu
 */
public interface Ordering<T> {
    T next();
    T prev();
    void skipTo(T item);
}
