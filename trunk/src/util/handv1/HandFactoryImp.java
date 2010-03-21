package src.util.handv1;

import src.util.HandFactory;
import src.util.Hand;
import src.util.HasComparable;

/**
 *
 * @author kagioglu
 */
public class HandFactoryImp implements HandFactory {
    public <T extends HasComparable> Hand<T> make(Class<T> clazz) {
        return new HandImp<T>(clazz);
    }
}
