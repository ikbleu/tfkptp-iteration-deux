package src.model.control;
import src.control.*;

public interface BindingMapBuilder {
	public void context(String context);
	public void binding(KeyCodeAndModifiers event, String meaning); 
}
