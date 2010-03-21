package src.util;

/**
 *
 * @author kagioglu
 */
public interface Ordering<T> {
    void next();
    void prev();
    void skipTo(T item);
    T current();
}
