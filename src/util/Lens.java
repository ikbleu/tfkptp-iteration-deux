package src.util;

import java.util.Collection;

/**
 *
 * @author kagioglu
 */
public interface Lens<T extends HasComparable> {
    void fill(Collection<T> coll);
    void next(DataVisitor<T> visitor);
    void prev(DataVisitor<T> visitor);
    void pick(T item, DataVisitor<T> visitor);
    void accept(DataVisitor<T> visitor);
}
