package src.control.interfaces;

/**
 *
 * @author kagioglu
 */
public interface KeyEventInterpreter {
    void interpret(InterpretableKeyConfig ikc);
    void editAncestor(KeyEditor editor);
    void editDescendant(KeyEditor editor);
}
