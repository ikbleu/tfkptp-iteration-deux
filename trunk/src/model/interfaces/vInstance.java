package src.model.interfaces;

public interface vInstance 
{
	public void accept( InstanceVisitor v );
	
	public void addViewListener( ViewListener vl );
}
