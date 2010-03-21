package src.model.interfaces;

import src.model.Player;
import src.model.interfaces.HasPlayerVisitor;

public interface HasPlayer 
{
	public Player getPlayer();
	public void accept(HasPlayerVisitor hpv);
}
