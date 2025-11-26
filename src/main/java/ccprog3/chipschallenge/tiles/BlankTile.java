package ccprog3.chipschallenge.tiles;

import ccprog3.chipschallenge.gamefiles.Chip;
import ccprog3.chipschallenge.gamefiles.Item;
import ccprog3.chipschallenge.gamefiles.Map;
import javafx.scene.image.Image;

/**
 * Represents a blank, passable floor tile ('_').
 * This tile is the standard empty space on the map. It can also hold
 * a collectible {@link Item} (like a key or a chip).
 */
public class BlankTile extends Tile {

     /**
     * Constructs a new BlankTile that does not contain an item.
     * It is marked as passable.
     */
    public BlankTile(){
        super(true);
        image = new Image(getClass().getResourceAsStream("/ccprog3/chipschallenge/images/blank.png"));
    }

     /**
     * Constructs a new BlankTile that holds a specific {@link Item}.
     * It is marked as passable.
     *
     * @param item The {@link Item} to be placed on this tile.
     */
    public BlankTile(Item item){
        super(true,item);
        image = new Image(getClass().getResourceAsStream("/ccprog3/chipschallenge/images/blank.png"));

    }

     /**
     * Gets the character symbol for this tile.
     * If the tile holds an {@link Item}, it returns the item's symbol (e.g., 'c', 'r').
     * If the tile is empty, it returns the symbol for a blank floor ('_').</li>
     *
     * @return The symbol of the item on the tile, or '_' if the tile is empty.
     */
    @Override
    public char getSymbol(){
        // if blank tile has an item return the item symbol instead
        return item != null ? item.getSymbol() : '_'; 
    }

     /**
     * Defines Chip's interaction with this tile.
     * If the tile contains an {@link Item}, Chip picks up the item,
     * adding it to his {@link ccprog3.chipschallenge.gamefiles.Inventory}. The item is then
     * removed from the tile.
     *
     * @param chip The {@link Chip} instance interacting with the tile.
     * @param map  The {@link Map} (not used in this interaction but required by the method signature).
     */
    @Override 
    public void interact(Chip chip, Map map) {
        if (item != null){
            chip.pickup(item);
            item = null;
            System.out.println("You have picked up an item.");
        }
    }

    /**
     * Retrieves the image to be displayed for this tile.
     * If an item is present on the tile, the item's image is returned.
     * Otherwise, the tile's base image is returned.
     *
     * @return The Image object to render.
     */
    public Image getImage(){
        return item != null ? item.getImage() : this.image;
    }

}