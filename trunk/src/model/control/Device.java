package src.model.control;


/**
 *
 * @author kagioglu
 */
public interface Device {
    String context();
    String meaning();
    void direct(KeyEventInterpreterBuilder builder);
}
