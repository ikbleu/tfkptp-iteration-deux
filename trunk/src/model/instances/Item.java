package src.model.instances;

import src.model.enums.ItemType;

public abstract class Item extends Locatable
{
	ItemType type;
	
	public Item(ItemType type)
	{
		this.type = type;
	}
	
	public abstract void accept(/* ?? */);
	public abstract boolean blocksTile();
}
