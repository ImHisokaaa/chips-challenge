package ccprog3.chipschallenge.tiles;

import ccprog3.chipschallenge.gamefiles.Chip;
import ccprog3.chipschallenge.gamefiles.Map;
import javafx.scene.image.Image;

/**
 * Represents a fire tile ('F') on the map.
 * This tile is technically passable, but it will kill Chip if he
 * attempts to step on it without {@link gamefiles.Inventory#hasFireBoots() fire boots}.
 */
public class FireTile extends Tile {

     /**
     * Constructs a new FireTile.
     * It is initialized as passable (though dangerous).
     */
    public FireTile(){
        super(true);
        image = new Image(getClass().getResourceAsStream("/ccprog3/chipschallenge/images/fire.png"));
    }

     /**
     * Defines Chip's interaction upon entering the fire tile.
     * Checks if Chip's inventory contains fire boots.
     * If Chip does not have fire boots, {@link Chip#die(String)} is called.
     *
     * @param chip The {@link Chip} instance interacting with the tile.
     * @param map  The {@link Map} (not used in this interaction but required by the method signature).
     */
    @Override
    public void interact(Chip chip, Map map){
        if(!chip.getInventory().hasFireBoots())
            chip.die("Chip has Burned.");
    }

     /**
     * Gets the character symbol for this tile.
     *
     * @return Always returns 'F'.
     */
    @Override
    public char getSymbol(){
        return 'F';
    }

    @Override
    public Image getImage() {
        return image;
    }
}