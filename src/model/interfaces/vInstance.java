package src.model.interfaces;

public interface vInstance 
{
	public void accept( vInstanceVisitor v );
	
	public void addViewListener( ViewListener vl );
}
