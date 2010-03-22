package src.model.control;

import src.model.interfaces.Displayable;
import src.util.HasComparable;

/**
 *
 * @author kagioglu
 */
public interface Behavior extends HasComparable, Displayable {
    String meaning();
    void execute();
}
