package ccprog3.chipschallenge.tiles;

import ccprog3.chipschallenge.gamefiles.Chip;
import ccprog3.chipschallenge.gamefiles.Map;
import ccprog3.chipschallenge.gamefiles.Item;
import javafx.scene.image.Image;

/**
 * Represents the abstract base class for all tiles on the game map.
 * This class defines the fundamental properties of a tile, such as
 * whether it is passable and whether it holds an item.
 * Subclasses must implement {@link #getSymbol()} to define their
 * visual representation and {@link #interact(Chip, Map)} to define
 * what happens when Chip lands on them.
 */
public abstract class Tile{ // this class will be inherited by other tile classes

    protected boolean isPassable = true;
    protected Item item = null;
    protected Image image;

     /**
     * Constructs a Tile, setting only its passability.
     * Assumes the tile holds no item by default.
     *
     * @param isPassable {@code true} if the tile can be moved onto, {@code false} otherwise.
     */
    public Tile(boolean isPassable){
        this.isPassable = isPassable;
    }

     /**
     * Constructs a Tile, setting both its passability and the item it holds.
     *
     * @param isPassable {@code true} if the tile can be moved onto, {@code false} otherwise.
     * @param item       The {@link Item} to place on this tile.
     */
    public Tile(boolean isPassable, Item item){
        this.isPassable = isPassable;
        this.item = item;
    }

    public abstract Image getImage();

    /**
     * Checks if this tile is passable.
     *
     * @return {@code true} if Chip can move onto this tile, {@code false} otherwise.
     */
    public boolean isPassable(){
        return this.isPassable;
    }

     /**
     * Gets the item currently on this tile.
     *
     * @return The {@link Item} object, or {@code null} if no item is present.
     */
    public Item getItem(){
        return this.item;
    }

     /**
     * Abstract method to get the character symbol representing this tile on the map.
     *
     * @return The character symbol (e.g., '#', '_', 'E').
     */
    public abstract char getSymbol();

     /**
     * Abstract method defining the interaction that occurs when Chip lands on this tile.
     * This method is called after Chip successfully moves onto the tile.
     *
     * @param chip The {@link Chip} instance interacting with the tile.
     * @param map  The {@link Map} context, in case the interaction needs it.
     */
    public abstract void interact(Chip chip, Map map);




}