package src.model.instances;

import src.model.interfaces.GameTile;
import src.model.interfaces.LocatableVisitor;
import src.model.interfaces.ItemVisitor;

public abstract class Item extends Locatable
{
	String type;
	
	public Item(String type, GameTile location)
	{
            super(location);
            this.type = type;
	}
	
	public void accept(LocatableVisitor lv)
        {
            lv.visitItem(this);
        }

        public abstract void accept(ItemVisitor iv);

	public abstract boolean blocksTile();

        public String token()
        {
            return type;
        }
}
