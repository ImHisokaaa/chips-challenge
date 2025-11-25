package ccprog3.chipschallenge.tiles;

import ccprog3.chipschallenge.gamefiles.Chip;
import ccprog3.chipschallenge.gamefiles.Map;
import javafx.scene.image.Image;

/**
 * Represents a red door tile ('R').
 * This door requires a red key from Chip's inventory to be opened.
 * It inherits functionality from {@link DoorTile}.
 */
public class RedDoor extends DoorTile {

     /**
     * Constructs a new RedDoor.
     * It is initialized as "Red", locked, and not passable by default.
     */
    public RedDoor(){
        super("Red");
        isLocked = true;
        image = new Image(getClass().getResourceAsStream("/ccprog3/chipschallenge/images/redDoor.png"));

    }

     /**
     * Gets the character symbol for this tile.
     *
     * @return 'R' if the door is locked, or '_' (blank tile) if it has been unlocked.
     */
    @Override
    public char getSymbol(){
        return isLocked ? 'R' : '_';
    }

     /**
     * Defines Chip's interaction with the red door.
     * If the door is locked, it checks if Chip has a red key
     * (using {@link ccprog3.chipschallenge.gamefiles.Inventory#useRed()}).
     * If Chip has a key, the key is consumed, the door is permanently unlocked
     * ({@code isLocked = false}), and it becomes passable.
     * If Chip does not have a key, a message is printed, and the door remains locked.
     *
     * @param chip The {@link Chip} instance interacting with the door.
     * @param map  The {@link Map} (not used in this interaction but required by the method signature).
     */
    @Override
    public void interact(Chip chip, Map map){
        if(this.isLocked && chip.getInventory().useRed()) { // if chip can unlock the locked door
            this.isLocked = false;
            this.isPassable = true;
            image = new Image(getClass().getResourceAsStream("/ccprog3/chipschallenge/images/blank.png"));
            System.out.println("You have unlocked the door.");
        }
        else if (this.isLocked && !chip.getInventory().useRed()){
            System.out.println("You need a Red Key.");
        }
    }

    @Override
    public Image getImage() {
        return image;
    }
}
