package src.util;

/**
 *
 * @author kagioglu
 */
public interface DataVisitor<T> {
    void found(T item);
    void invalid(T item);
    void missing();
}
