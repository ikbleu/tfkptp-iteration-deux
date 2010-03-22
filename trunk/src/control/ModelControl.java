package src.control;
import src.model.control.KeyEventInterpreterBuilder;
import src.control.interfaces.ParentKeyEventInterpreter;
import src.control.interfaces.KeyEventInterpreter;
import src.control.interfaces.KeyMapVisitor;
import src.control.interfaces.InjectionBuilder;
import src.control.interfaces.InterpretableKeyConfig;
import src.control.interfaces.KeyEditor;
import src.util.DataVisitor;
import src.util.Lens;
import src.model.control.Device;

import java.util.Map;
import java.util.Hashtable;
import java.util.Collection;
import java.util.LinkedList;

/**
 *
 * @author kagioglu
 */
class ModelControl implements
    ParentKeyEventInterpreter,
    KeyEventInterpreter,
    DataVisitor<Device>,
    KeyMapVisitor
{
    private static final String NEXTDEVICE = "NextDevice";
    private static final String PREVDEVICE = "PrevDevice";

    private final String context;
    private final Lens<Device> lens;
    private final Map<String,KeyEventInterpreterBuilder> buildermap;
    private final InjectionBuilder injectionbuilder;

    private KeyEventInterpreter fallback;

    ModelControl(
        String context,
        Lens<Device> lens,
        Map<String,KeyEventInterpreterBuilder> buildermap,
        InjectionBuilder injectionbuilder
    ) {
        this.context = context;
        this.lens = lens;
        this.buildermap = buildermap;
        this.injectionbuilder = injectionbuilder;
    }

    public void setFallback(KeyEventInterpreter fallback) {
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

    public void found(Device device) {
        String con = device.context();
        if(this.buildermap.containsKey(con)) {
            this.injectionbuilder.start(con);
            device.direct(this.buildermap.get(con));
        }
        else { throw new RuntimeException("builder not found!"); }
    }
    public void missing() { this.injectionbuilder.injectNothing(); }

    public void contextUnknown() { throw new RuntimeException("context unknown!"); }
    public void meaningUnknown() {
        this.lens.accept(this);
        this.fallback.interpret(this.tempikc);
    }
    public void foundMeaning(String meaning) {
        if(meaning.compareTo(ModelControl.NEXTDEVICE) == 0) {
            this.lens.next(this);
        }
        else {
            if(meaning.compareTo(ModelControl.PREVDEVICE) == 0) {
                this.lens.prev(this);
            }
            else {
                Collection<Device> tempall = new LinkedList<Device>();
                this.lens.fill(tempall);
                Map<String,Device> decode = new Hashtable<String,Device>();
                for(Device dev : tempall) {
                    decode.put(dev.meaning(), dev);
                }
                if(decode.containsKey(meaning)) {
                    this.lens.pick(decode.get(meaning), this);
                }
                else { /*DO NOTHING*/ }
            }
        }
    }
}
