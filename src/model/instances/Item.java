package src.model.instances;

import src.model.enums.ItemType;
import src.model.interfaces.GameTile;

public abstract class Item extends Locatable
{
	ItemType type;
	
	public Item(ItemType type, GameTile location)
	{
            super(location);
            this.type = type;
	}
	
	public abstract void accept(/* ?? */);
	public abstract boolean blocksTile();
}
