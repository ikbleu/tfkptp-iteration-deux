package src.control.interfaces;

import src.model.control.Behavior;
import src.model.control.Device;
import src.util.Lens;

/**
 *
 * @author kagioglu
 */
public interface KeyEventInterpreterBuilder {
    void devices(Lens<Device> lens);
    void behaviors(Lens<Behavior> lens);
}
