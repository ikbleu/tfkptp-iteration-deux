package src.control.interfaces;

/**
 * This class will translate the contexts that model provides to the
 * contexts that view expects.
 *
 * @author kagioglu
 */
public interface ContextTranslator {
    String translate(String input);
}
