package src.model.interfaces;

import src.model.Player;

public interface InstanceHolder
{
	public void applyToAll(InstanceFunction iF, Player p);
}
