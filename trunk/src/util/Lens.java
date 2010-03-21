package src.util;

/**
 *
 * @author kagioglu
 */
public interface Lens<T extends HasComparable> {
    void next(DataVisitor<T> visitor);
    void prev(DataVisitor<T> visitor);
    void pick(T item, DataVisitor<T> visitor);
    void accept(DataVisitor<T> visitor);
}
