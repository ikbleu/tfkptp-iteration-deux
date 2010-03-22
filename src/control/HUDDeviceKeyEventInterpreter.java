package src.control;

import src.control.interfaces.KeyEditor;
import src.control.interfaces.InterpretableKeyConfig;
import src.control.interfaces.KeyEventInterpreter;
import src.control.interfaces.KeyMapVisitor;
import src.control.interfaces.InjectionBuilder;
import src.util.Lens;
import src.util.DataVisitor;
import src.model.control.Device;
import src.model.control.Behavior;
import src.model.control.KeyEventInterpreterBuilder;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.Hashtable;

/**
 *
 * @author kagioglu
 */
class HUDDeviceKeyEventInterpreter implements
    KeyEventInterpreterBuilder,
    KeyEventInterpreter,
    DataVisitor<Device>,
    KeyMapVisitor
{
    private final String context;
    private final Lens<Device> lens;
    private final Map<String,Device> decode;
    private final InjectionBuilder injectionbuilder;

    private static final String NEXTDEVICE = "NextDevice";
    private static final String PREVDEVICE = "PrevDevice";

    private KeyEventInterpreter fallback;
    
    HUDDeviceKeyEventInterpreter(
        String context,
        Lens<Device> lens,
        InjectionBuilder injectionbuilder
    ) {
        this.context = context;
        this.lens = lens;
        this.decode = new Hashtable<String,Device>();
        this.injectionbuilder = injectionbuilder;
        this.lens.accept(this);
    }

    public void devices(Lens<Device> lens) {
        this.fallback = new HUDDeviceKeyEventInterpreter(
            this.tempcontext,
            lens,
            this.injectionbuilder
        );
    }
    public void behaviors(Lens<Behavior> lens) {
        this.fallback = new HUDBehaviorKeyEventInterpreter(
            this.tempcontext,
            lens,
            this.injectionbuilder
        );
    }

    private InterpretableKeyConfig tempikc = null;

    public void interpret(InterpretableKeyConfig ikc) {
        this.tempikc = ikc;
        ikc.interpret(this.context, this);
        this.tempikc = null;
    }
    public void editAncestor(KeyEditor editor) { throw new RuntimeException("oops!"); }
    public void editDescendant(KeyEditor editor) { throw new RuntimeException("oops!"); }

    private String tempcontext = null;

    public void found(Device device) {
        this.tempcontext = device.context();
        this.injectionbuilder.element(device);
        this.injectionbuilder.selection(device);
        device.direct(this);
        this.tempcontext = null;
    }
    public void missing() {
        this.injectionbuilder.inject();
    }

    public void contextUnknown() { throw new RuntimeException("context unknown!"); }
    public void meaningUnknown() {
        this.lens.accept(this);
        this.fallback.interpret(this.tempikc);
    }
    public void foundMeaning(String meaning) {
        if(meaning.compareTo(HUDDeviceKeyEventInterpreter.NEXTDEVICE) == 0) {
            this.lens.next(this);
        }
        else {
            if(meaning.compareTo(HUDDeviceKeyEventInterpreter.PREVDEVICE) == 0) {
                this.lens.prev(this);
            }
            else {
                Collection<Device> tempall = new LinkedList<Device>();
                this.lens.fill(tempall);
                this.decode.clear();
                for(Device dev : tempall) {
                    this.decode.put(dev.meaning(), dev);
                }
                if(this.decode.containsKey(meaning)) {
                    this.lens.pick(this.decode.get(meaning), this);
                }
                else { /*DO NOTHING*/ }
            }
        }
    }
}
