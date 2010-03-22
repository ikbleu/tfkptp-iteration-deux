package src.control.interfaces;

import src.control.KeyCodeAndModifiers;

public interface BindingMapBuilder {
    void start();
	void context(String context);
	void binding(KeyCodeAndModifiers event, String meaning);
    void end();
}
