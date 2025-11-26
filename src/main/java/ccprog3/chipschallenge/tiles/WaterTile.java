package ccprog3.chipschallenge.tiles;

import ccprog3.chipschallenge.gamefiles.Chip;
import ccprog3.chipschallenge.gamefiles.Map;
import javafx.scene.image.Image;

/**
 * Represents a water tile ('~') on the map.
 * This tile is technically passable, but it will kill Chip if he
 * attempts to step on it without {@link ccprog3.chipschallenge.gamefiles.Inventory#hasFlippers() flippers}.
 */
public class WaterTile extends Tile {

     /**
     * Constructs a new WaterTile.
     * It is initialized as passable (though dangerous).
     */
    public WaterTile(){
        super(true);
        image = new Image(getClass().getResourceAsStream("/ccprog3/chipschallenge/images/water.png"));

    }

     /**
     * Defines Chip's interaction upon entering the water tile.
     * Checks if Chip's inventory contains flippers.
     * If Chip does not have flippers, {@link Chip#die(String)} is called.
     *
     * @param chip The {@link Chip} instance interacting with the tile.
     * @param map  The {@link Map} (not used in this interaction but required by the method signature).
     */
    @Override
    public void interact(Chip chip, Map map){
        if (!chip.getInventory().hasFlippers())
            chip.die("Chip has Drowned");
    }

     /**
     * Gets the character symbol for this tile.
     *
     * @return Always returns '~'.
     */
    @Override
    public char getSymbol(){
        return '~';
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