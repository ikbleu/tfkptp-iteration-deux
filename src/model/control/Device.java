package src.model.control;

import src.model.interfaces.Displayable;
import src.util.HasComparable;


/**
 *
 * @author kagioglu
 */
public interface Device extends HasComparable, Displayable {
    String context();
    String meaning();
    void direct(KeyEventInterpreterBuilder builder);
}
