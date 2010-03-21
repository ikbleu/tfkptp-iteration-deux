package src.util;

/**
 *
 * @author kagioglu
 */
public interface HandFactory {
    <T extends HasComparable> Hand<T> make(Class<T> clazz);
}
