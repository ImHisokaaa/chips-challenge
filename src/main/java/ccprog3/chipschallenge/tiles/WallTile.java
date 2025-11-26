package ccprog3.chipschallenge.tiles;

import ccprog3.chipschallenge.gamefiles.Chip;
import ccprog3.chipschallenge.gamefiles.Map;
import javafx.scene.image.Image;

/**
 * Represents a wall tile ('#') on the map.
 * Walls are impassable barriers that block Chip's movement.
 */
public class WallTile extends Tile{
    /**
     * Constructs a new WallTile.
     * It is always initialized as not passable.
     */
    public WallTile(){
        super(false);
        image = new Image(getClass().getResourceAsStream("/ccprog3/chipschallenge/images/wall.png"));
    }

    /**
     * Gets the character symbol for this tile.
     *
     * @return Always returns '#'.
     */
    @Override
    public char getSymbol(){
        return '#';
    }

     /**
     * Defines Chip's interaction with the wall tile.
     * Since walls are impassable, Chip can never move onto this tile,
     * so this interaction method is empty and does nothing.
     *
     * @param chip The {@link Chip} instance (not used).
     * @param map  The {@link Map} (not used).
     */
    @Override
    public void interact(Chip chip, Map map){

    }

    /**
     * Retrieves the visual image for this tile.
     *
     * @return The Image object.
     */
    @Override
    public Image getImage() {
        return image;
    }
}
