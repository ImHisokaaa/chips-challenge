package ccprog3.chipschallenge.tiles;

import ccprog3.chipschallenge.gamefiles.Chip;
import ccprog3.chipschallenge.gamefiles.Map;
import javafx.scene.image.Image;

/**
 * Represents a hazardous acid (poison) tile.
 * This tile is passable, but kills the player unless they have the required protection.
 */
public class AcidTile extends Tile{

    /**
     * Constructs a new AcidTile.
     * Initializes the tile as passable and loads the acid image.
     */
    public AcidTile(){
        super(true);
        image = new Image(getClass().getResourceAsStream("/ccprog3/chipschallenge/images/acid.png"));
    }

    /**
     * Gets the character symbol representing this tile on the map.
     *
     * @return The character 'i'.
     */
    @Override
    public char getSymbol(){
        return 'i';
    }

    /**
     * Gets the visual sprite for the acid tile.
     *
     * @return The Image object.
     */
    @Override
    public Image getImage(){
        return this.image;
    }

    /**
     * Handles the interaction when Chip steps on this tile.
     * Checks if the player has "Acid Boots" (suction shoes).
     * If the boots are missing, Chip dies immediately.
     *
     * @param chip The player character.
     * @param map  The current game map.
     */
    @Override
    public void interact(Chip chip, Map map){
        if(!chip.getInventory().hasAcidBoots())
            chip.die("Chip has Been Poisoned.");
    }
}
