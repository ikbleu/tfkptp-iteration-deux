package src.control.interfaces;

/**
 *
 * @author kagioglu
 */
public interface EditableKeyEventInterpreter {
    void directMeToYourParent(KeyEditor editor);
    void directMeToYourChild(KeyEditor editor);
    void nextSet(KeyEventInterpreterEditor editor);
    void prevSet(KeyEventInterpreterEditor editor);
    void nextElement(KeyEventInterpreterEditor editor);
    void prevElement(KeyEventInterpreterEditor editor);
}
