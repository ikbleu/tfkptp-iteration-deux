package src.control;

import src.control.interfaces.KeyEventInterpreter;
import src.control.interfaces.InterpretableKeyConfig;
import src.control.interfaces.KeyMapVisitor;
import src.control.interfaces.KeyEditor;
import src.control.interfaces.Function;

import java.util.Map;

/**
 * quick and dirty implementation of the root key event interpreter.
 * lets get it done!
 * @author kagioglu
 */
class Rooty implements KeyEventInterpreter, KeyMapVisitor {
    private final String context;
    private final Map<String,Function> decision;
    private KeyEventInterpreter fallback;

    Rooty(
        String context,
        Map<String,Function> decision,
        KeyEventInterpreter fallback
    ) {
        this.context = context;
        this.decision = decision;
        this.fallback = fallback;
    }

    private InterpretableKeyConfig tempikc = null;

    public void interpret(InterpretableKeyConfig ikc) {
        this.tempikc = ikc;
        ikc.interpret(this.context, this);
        this.tempikc = null;
    }
    public void editAncestor(KeyEditor editor) { throw new RuntimeException("oops!"); }
    public void editDescendant(KeyEditor editor) { throw new RuntimeException("oops!"); }

    public void contextUnknown() {
        throw new RuntimeException("context unknown!");
    }
    public void meaningUnknown() { this.fallback.interpret(this.tempikc); }
    public void foundMeaning(String meaning) {
        if(this.decision.containsKey(meaning)) {
            this.decision.get(meaning).execute();
        }
        else { /*DO NOTHING*/ }
    }
}
