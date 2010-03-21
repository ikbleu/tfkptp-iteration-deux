package src.util;

/**
 *
 * @author kagioglu
 */
public interface DataVisitor<T> {
    void found(T item);
    void missing();
}
