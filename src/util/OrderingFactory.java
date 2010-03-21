package src.util;

/**
 *
 * @author kagioglu
 */
public interface OrderingFactory<T> {
    Ordering<T> make(DoYouHas<T> dyh);
}
