package src.util;

/**
 *
 * @author kagioglu
 */
public interface Hand<T extends HasComparable> {
    boolean add(T item);
    boolean remove(T item);
    Lens<T> spawnLens();
    void clear();
}
