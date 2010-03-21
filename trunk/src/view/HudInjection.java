package src.view;

import src.model.interfaces.Displayable;


class HudInjection implements ViewInjectionElement {
	

	public void injectionEffect(ScreenManager sm, String code,
			Displayable[] direct, Displayable[] dList, Displayable selection) {
		
			sm.updateStatusOverview(dList);
		
	}
	
}