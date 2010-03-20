package src.model.interfaces;

public interface ViewListener {
	public void locationChanged( vInstance i, GameTile prev );
	public void healthChanged( vInstance i );
	public void statsChanged( vInstance i );
}
