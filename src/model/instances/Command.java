package src.model.instances;


public interface Command {
	public String token();
	public Instance instance();
	public void accept( CommandVisitor c );
}
