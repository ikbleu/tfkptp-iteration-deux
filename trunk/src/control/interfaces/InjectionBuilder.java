package src.control.interfaces;

import src.model.interfaces.Displayable;

/**
 *
 * @author kagioglu
 */
public interface InjectionBuilder {
    void start(String context);
    void directory(Displayable disp);
    void element(Displayable disp);
    void selection(Displayable disp);
    void inject();
    void injectNothing();
}
