package src.util;

/**
 *
 * @author kagioglu
 */
public interface HandFactory {
    <T> Hand<T> make(Class<T> clazz);
}
