package src.model.interfaces;

public interface Clock
{
	public void registerPrimary(Tickable ticker);
	public void registerSecondary(Tickable ticker);
	public void remove(Tickable ticker);
	public void start();
    
    public void stop();

}
