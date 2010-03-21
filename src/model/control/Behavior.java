package src.model.control;

import src.util.HasComparable;

/**
 *
 * @author kagioglu
 */
public interface Behavior extends HasComparable {
    String meaning();
    void execute();
}
