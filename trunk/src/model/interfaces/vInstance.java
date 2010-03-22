package src.model.interfaces;

import src.model.control.Device;

public interface vInstance extends Device
{
	public void accept( vInstanceVisitor v );
	
	public void addViewListener( ViewListener vl );
	
	public String getCurrentAction();
	
	public String token();
	public int id();
}
