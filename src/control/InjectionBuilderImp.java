package src.control;

import src.control.interfaces.InjectionBuilder;
import src.control.interfaces.ContextTranslator;
import src.view.ViewInjection;
import src.model.interfaces.Displayable;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author kagioglu
 */
class InjectionBuilderImp implements InjectionBuilder {
    private final ViewInjection viewinj;
    private final ContextTranslator translator;
    private String context;
    private final List<Displayable> directory;
    private final List<Displayable> element;
    private Displayable selection;
    InjectionBuilderImp(ViewInjection viewinj, ContextTranslator translator) {
        this.viewinj = viewinj;
        this.translator = translator;
        this.context = null;
        this.directory = new LinkedList<Displayable>();
        this.element = new LinkedList<Displayable>();
        this.selection = null;
    }
    public void start(String context) {
        this.context = this.translator.translate(context);
    }
    public void directory(Displayable disp) { this.directory.add(disp); }
    public void element(Displayable disp) { this.element.add(disp); }
    public void selection(Displayable disp) { this.selection = disp; }
    public void inject() {
        this.viewinj.injectionFairyLily(
            this.context,
            this.directory.toArray(new Displayable[0]),
            this.element.toArray(new Displayable[0]),
            this.selection
        );
        this.reset();
    }
    public void injectNothing() {
        this.viewinj.injectionCloseMenu();
        this.reset();
    }
    private void reset() {
        this.context = null;
        this.directory.clear();
        this.element.clear();
        this.selection = null;
    }
}
