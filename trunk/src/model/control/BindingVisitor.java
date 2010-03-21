package src.model.control;

public interface BindingVisitor {
	public void contextUnknown();
	public void meaningUnknown();
	public void foundMeaning(String meaning);
}
