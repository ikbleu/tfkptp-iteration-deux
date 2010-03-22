package src.control;

import src.util.Lens;
import src.model.control.Device;
import src.model.control.Behavior;
import src.model.control.KeyEventInterpreterBuilder;
import src.control.fakemodel.Ousput;
import src.control.interfaces.InjectionBuilder;
import src.control.fakemodel.FakeModelConfig;
import src.control.interfaces.KeyEventInterpreter;
import src.control.interfaces.ParentKeyEventInterpreter;
import src.control.fakeview.NoExceptionInjection;
import src.view.ViewInjection;
import src.control.interfaces.Function;

import java.util.Hashtable;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author kagioglu
 */
public class ControllerTestConfigurer {
    public static KeyEventInterpreter root() {
        ViewInjection viewinj = new NoExceptionInjection();

        HashtableTranslator trans = new HashtableTranslator();
        ContextTranslatorConfigurer.configure(trans);
        InjectionBuilder sib = new InjectionBuilderImp(viewinj, trans);
        
        return ControllerTestConfigurer.makemordel(sib);
    }
    
    private static KeyEventInterpreter makemordel(
        InjectionBuilder someinjbuilder
    ) {
        class Kibby implements KeyEventInterpreterBuilder {
            private final String context;
            private final InjectionBuilder ijb;
            private final ParentKeyEventInterpreter par;
            Kibby(
                String context,
                InjectionBuilder ijb,
                ParentKeyEventInterpreter par
            ) {
                this.context = context;
                this.ijb = ijb;
                this.par = par;
            }
            public void devices(Lens<Device> lens) {
                this.par.setFallback(new HUDDeviceKeyEventInterpreter(
                    this.context,
                    lens,
                    this.ijb
                ));
            }
            public void behaviors(Lens<Behavior> lens) {
                this.par.setFallback(new HUDBehaviorKeyEventInterpreter(
                    this.context,
                    lens,
                    this.ijb
                ));
            }
        }

        Hashtable<String,KeyEventInterpreterBuilder> map = new Hashtable<String,KeyEventInterpreterBuilder>();
        List<Device> secretlist = new ArrayList<Device>();
        ModelControl mormdorm = new ModelControl(
            "ModelControl",
            FakeModelConfig.make(new Ousput(), secretlist),
            map,
            someinjbuilder
        );
        for(Device devivo : secretlist) {
            map.put(devivo.context(), new Kibby(devivo.context(), someinjbuilder, mormdorm));
        }
        return ControllerTestConfigurer.makemenu(mormdorm);
    }

    private static KeyEventInterpreter makemenu(KeyEventInterpreter modelocon) {
        Hashtable<String,KeyEventInterpreter> hasho = new Hashtable<String,KeyEventInterpreter>();
        hasho.put("Model", modelocon);
        KeyEventInterpreter menudenu = new MenuBranchKeyEventInterpreter(
            "Menu",
            hasho
        );
        return ControllerTestConfigurer.makeroot(menudenu);
    }

    private static KeyEventInterpreter makeroot(KeyEventInterpreter fallback) {
        Hashtable<String,Function> funs = new Hashtable<String,Function>();
        class Escapa implements Function {
            public void execute() { System.exit(0); }
        }
        funs.put("Escape", new Escapa());
        return new Rooty("Root", funs, fallback);
    }
}
