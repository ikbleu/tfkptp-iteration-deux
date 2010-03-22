package src.control.fakeview;

import src.view.ViewInjection;
import src.model.interfaces.Displayable;

/**
 *
 * @author kagioglu
 */
public class NoExceptionInjection implements ViewInjection {
    public void injectionFairyLily(
        String context,
        Displayable[] directory,
        Displayable[] element,
        Displayable selection
    ) {}
    public void injectionCloseMenu() {}
	@Override
	public void toggleViewPort() {
		// TODO Auto-generated method stub
		
	}
}
