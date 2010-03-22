package src.control;

import src.control.interfaces.KeyEventInterpreter;
import src.control.interfaces.InterpretableKeyConfig;
import src.control.interfaces.KeyEditor;

/**
 * quick and dirty implementation of the root key event interpreter.
 * lets get it done!
 * @author kagioglu
 */
class Rooty implements KeyEventInterpreter {
    public void interpret(InterpretableKeyConfig ikc) {}
    public void editAncestor(KeyEditor editor) {}
    public void editDescendant(KeyEditor editor) {}
}
