package src.control;

import src.control.interfaces.KeyEditor;
import src.control.interfaces.InterpretableKeyConfig;
import src.control.interfaces.KeyEventInterpreter;
import src.control.interfaces.KeyMapVisitor;
import src.control.interfaces.InjectionBuilder;
import src.util.Lens;
import src.util.DataVisitor;
import src.model.control.Behavior;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.Hashtable;

/**
 *
 * @author kagioglu
 */
public class HUDBehaviorKeyEventInterpreter implements
    KeyEventInterpreter,
    DataVisitor<Behavior>,
    KeyMapVisitor
{
    private final String context;
    private final Lens<Behavior> lens;
    private final Map<String,Behavior> decode;
    private final InjectionBuilder injectionbuilder;

    private static final String NEXTBEHAVIOR = "NextBehavior";
    private static final String PREVBEHAVIOR = "PrevBehavior";

    HUDBehaviorKeyEventInterpreter(
        String context,
        Lens<Behavior> lens,
        InjectionBuilder injectionbuilder
    ) {
        this.context = context;
        this.lens = lens;
        this.decode = new Hashtable<String,Behavior>();
        this.injectionbuilder = injectionbuilder;
    }

    private InterpretableKeyConfig tempikc = null;

    public void interpret(InterpretableKeyConfig ikc) {
        this.tempikc = ikc;
        ikc.interpret(this.context, this);
        this.tempikc = null;
    }
    public void editAncestor(KeyEditor editor) { throw new RuntimeException("oops!"); }
    public void editDescendant(KeyEditor editor) { throw new RuntimeException("oops!"); }

    public void found(Behavior behavior) {
        this.injectionbuilder.element(behavior);
        this.injectionbuilder.selection(behavior);
        behavior.execute();
        this.injectionbuilder.inject();
    }
    public void missing() {
        this.injectionbuilder.inject();
    }

    public void contextUnknown() { throw new RuntimeException("context unknown!"); }
    public void meaningUnknown() { /*DO NOTHING*/ }
    public void foundMeaning(String meaning) {
        if(meaning.compareTo(HUDBehaviorKeyEventInterpreter.NEXTBEHAVIOR) == 0) {
            this.lens.next(this);
        }
        else {
            if(meaning.compareTo(HUDBehaviorKeyEventInterpreter.PREVBEHAVIOR) == 0) {
                this.lens.prev(this);
            }
            else {
                Collection<Behavior> tempall = new LinkedList<Behavior>();
                this.lens.fill(tempall);
                this.decode.clear();
                for(Behavior beh : tempall) {
                    this.decode.put(beh.meaning(), beh);
                }
                if(this.decode.containsKey(meaning)) {
                    this.lens.pick(this.decode.get(meaning), this);
                }
                else { /*DO NOTHING*/ }
            }
        }
    }
}
