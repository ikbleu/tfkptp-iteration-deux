package src.control.interfaces;
import src.control.KeyCodeAndModifiers;

public interface EditableKeyConfig {
	public void bindMeaning(String context, String meaning, KeyCodeAndModifiers event);
	public boolean unbindMeaning(String context, String meaning);
	public void addContext(String context);
	public boolean removeContext(String context);
}
