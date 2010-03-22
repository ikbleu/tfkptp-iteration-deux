package src.control.interfaces;
import java.util.Iterator;

public interface BindingMapDirector {
	public void buildAll(BindingMapBuilder builder);
	public void buildForContext(Iterator<String>contexts, BindingMapBuilder builder);
}
