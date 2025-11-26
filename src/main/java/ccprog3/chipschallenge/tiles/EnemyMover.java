package ccprog3.chipschallenge.tiles;
import ccprog3.chipschallenge.gamefiles.Chip;
import ccprog3.chipschallenge.gamefiles.Map;
import javafx.scene.image.Image;

/**
 * Represents an invisible tile that changes the movement direction of Enemies.
 * Acts as a steering mechanism for enemies without affecting the player.
 */
public class EnemyMover extends Tile{

    private char direction; // 8 - up, 4 - left, 2 - down, 6 right

    /**
     * Constructs a new EnemyMover tile.
     * Sets the direction enemies should turn when entering this tile.
     *
     * @param dir The direction code ('8'=Up, '4'=Left, '2'=Down, '6'=Right).
     */
    public EnemyMover(char dir){
        super(true );
        this.direction = dir;
        image = new Image(getClass().getResourceAsStream("/ccprog3/chipschallenge/images/blank.png"));

    }

    /**
     * Gets the symbol representing this tile.
     *
     * @return The underscore character ('_').
     */
    @Override
    public char getSymbol(){return '_';}

    /**
     * Gets the direction code stored in this tile.
     *
     * @return The character representing the forced direction.
     */
    public char getDirection(){return this.direction;}

    /**
     * Handles interaction with the player.
     * This implementation is empty because this tile only affects Enemies, not Chip.
     *
     * @param chip The player character.
     * @param map  The current map.
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
