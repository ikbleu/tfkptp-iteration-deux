package src.model.instances;

import src.model.interfaces.GameTile;

public abstract class Item extends Locatable
{
	String type;
	
	public Item(String type, GameTile location)
	{
            super(location);
            this.type = type;
	}
	
	public abstract void accept(/* ?? */);
	public abstract boolean blocksTile();
}
