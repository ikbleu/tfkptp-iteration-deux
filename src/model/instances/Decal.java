/*
 * file: Decal.java
 */

package src.model.instances;

import src.model.enums.DecalType;
import src.model.interfaces.GameTile;

/**
 * Represents persistent effects on a tile.
 *
 * @author Christopher Dudley
 */
public abstract class Decal extends Locatable
{
    DecalType type;

    public Decal(DecalType type, GameTile location)
    {
        super(location);
        this.type = type;
    }
}
