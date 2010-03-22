package src.control.interfaces;
import src.control.*;

public interface EditableKeyConfig {
	public void bindMeaning(String context, String meaning, KeyCodeAndModifiers event);
	public boolean unbindMeaning(String context, String meaning);
	public boolean addContext(String context);
	public boolean removeContext(String context);
}
