package src.control.interfaces;

public interface KeyMapVisitor {

	public void contextUnknown();
	public void meaningUnknown();
	public void foundMeaning(String meaning);
}
