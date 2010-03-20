package src.model;

import src.model.enums.ItemType;

abstract class Item
{
	ItemType type;
	
	public Item(ItemType type)
	{
		this.type = type;
	}
	
	public abstract void accept(/* ?? */);
	public abstract boolean blocksTile();
}
