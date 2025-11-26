package ccprog3.chipschallenge.tiles;

import ccprog3.chipschallenge.gamefiles.Chip;
import ccprog3.chipschallenge.gamefiles.Map;
import javafx.scene.image.Image;

/**
 * Represents a blue door tile ('B').
 * This door requires a blue key from Chip's inventory to be opened.
 * It inherits functionality from {@link DoorTile}.
 */
public class BlueDoor extends DoorTile {

     /**
     * Constructs a new BlueDoor.
     * It is initialized as "Blue", locked, and not passable by default.
     */
    public BlueDoor(){
        super("Blue");
        this.isLocked = true;
        image = new Image(getClass().getResourceAsStream("/ccprog3/chipschallenge/images/blueDoor.png"));
    }

     /**
     * Gets the character symbol for this tile.
     *
     * @return 'B' if the door is locked, or '_' (blank tile) if it has been unlocked.
     */
    @Override
    public char getSymbol(){
        return isLocked ? 'B' : '_';
    }

    /**
     * Defines Chip's interaction with the blue door.
     * If the door is locked, it checks if Chip has a blue key
     * (using {@link ccprog3.chipschallenge.gamefiles.Inventory#useBlue()}).
     * If Chip has a key, the key is consumed, the door is permanently unlocked
     * ({@code isLocked = false}), and it becomes passable.
     * If Chip does not have a key, a message is printed, and the door remains locked.
     *
     * @param chip The {@link Chip} instance interacting with the door.
     * @param map  The {@link Map} (not used in this interaction but required by the method signature).
     */
    @Override
    public void interact(Chip chip, Map map){
        if(this.isLocked && chip.getInventory().useBlue()) { // if chip can unlock the locked door
            this.isLocked = false;
            this.isPassable = true;
            image = new Image(getClass().getResourceAsStream("/ccprog3/chipschallenge/images/blank.png"));
            System.out.println("You have unlocked the door.");
        }
        else if (this.isLocked && !chip.getInventory().useBlue()){
            System.out.println("You need a Blue Key.");
        }
    }

    /**
     * Retrieves the visual image associated with this object.
     *
     * @return The Image object to display.
     */
    @Override
    public Image getImage() {
        return image;
    }
}
