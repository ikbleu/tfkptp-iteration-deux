package src.model.control;

import src.util.HasComparable;


/**
 *
 * @author kagioglu
 */
public interface Device extends HasComparable {
    String context();
    String meaning();
    void direct(KeyEventInterpreterBuilder builder);
}
