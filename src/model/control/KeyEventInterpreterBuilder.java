package src.model.control;

import src.util.Lens;

/**
 *
 * @author kagioglu
 */
public interface KeyEventInterpreterBuilder {
    void devices(Lens<Device> lens);
    void behaviors(Lens<Behavior> lens);
}
