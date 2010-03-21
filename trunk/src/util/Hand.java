package src.util;

/**
 *
 * @author kagioglu
 */
public interface Hand<T extends HasComparable> {
    T add(T item);
    T remove(T item);
    Lens<T> spawnLens();
    void clear();
}
