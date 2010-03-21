package src.control.interfaces;

public interface InterpretableKeyConfig {
	void interpret(String context, KeyMapVisitor visitor);
}
