package src.control;
import java.util.List;
import src.control.interfaces.DisplayableBinding;

public interface KBinjection {
	public void displayAContextAndItsBindings(String context, List<DisplayableBinding> lst);
}
