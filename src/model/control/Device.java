package src.model.control;

import src.control.interfaces.KeyEventInterpreterBuilder;

/**
 *
 * @author kagioglu
 */
public interface Device {
    String context();
    String meaning();
    void direct(KeyEventInterpreterBuilder builder);
}
