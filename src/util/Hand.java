package src.util;

/**
 *
 * @author kagioglu
 */
public interface Hand<T> {
    boolean add(T item);
    boolean remove(T item);
    Lens<T> spawnLens();
}
