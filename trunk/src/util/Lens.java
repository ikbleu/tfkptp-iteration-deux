package src.util;

/**
 *
 * @author kagioglu
 */
public interface Lens<T> {
    Iter<T> iter(OrderingFactory<T> ordering);
}
