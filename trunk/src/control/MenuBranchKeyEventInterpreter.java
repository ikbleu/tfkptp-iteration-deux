package src.control;

import src.control.interfaces.KeyEventInterpreter;
import src.control.interfaces.InterpretableKeyConfig;
import src.control.interfaces.KeyMapVisitor;
import src.control.interfaces.KeyEditor;

import java.util.Map;

/**
 *
 * @author kagioglu
 */
class MenuBranchKeyEventInterpreter implements KeyEventInterpreter, KeyMapVisitor {
    private final String context;
    private final Map<String,KeyEventInterpreter> menus;
    private KeyEventInterpreter current;

    MenuBranchKeyEventInterpreter(
        String context,
        Map<String,KeyEventInterpreter> menus
    ) {
        this.context = context;
        this.menus = menus;
        this.current = null;
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
    public void meaningUnknown() {
        if(this.current != null) {
            if(this.menus.containsValue(this.current)) {
                this.current.interpret(this.tempikc);
            }
            else { this.current = null; }
        }
        else { /*DO NOTHING*/ }
    }
    public void foundMeaning(String meaning) {
        if(this.menus.containsKey(meaning)) {
            this.current = this.menus.get(meaning);
        }
        else { throw new RuntimeException("binding to non-existant menu"); }
    }
}
