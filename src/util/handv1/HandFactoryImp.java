package src.util.handv1;

import src.util.HandFactory;
import src.util.Hand;

/**
 *
 * @author kagioglu
 */
public class HandFactoryImp implements HandFactory {
    public <T> Hand<T> make(Class<T> clazz) { return new HandImp<T>(clazz); }
}
