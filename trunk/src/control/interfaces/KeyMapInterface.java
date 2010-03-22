package src.control.interfaces;
import src.control.KeyCodeAndModifiers;

public interface KeyMapInterface {
	public void getMeaning(String context,KeyMapVisitor visitor , KeyCodeAndModifiers key);
}
