package src.view;

import src.model.interfaces.Displayable;


class OverviewInjection implements ViewInjectionElement {


	public void injectionEffect(ScreenManager sm, String code,
			Displayable[] direct, Displayable[] dList, Displayable selection) {
		
		sm.updateOverview(code, direct, dList, selection);
		
	}
	
}