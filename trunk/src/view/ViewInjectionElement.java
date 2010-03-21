
package src.view;

import src.model.interfaces.Displayable;

public interface ViewInjectionElement {
	
	void injectionEffect(ScreenManager sm, String code,
            Displayable[] direct,
            Displayable[] dList,
            Displayable selection);
}