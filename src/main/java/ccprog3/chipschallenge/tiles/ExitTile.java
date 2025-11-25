package ccprog3.chipschallenge.tiles;

import ccprog3.chipschallenge.gamefiles.Chip;
import ccprog3.chipschallenge.gamefiles.Map;
import javafx.scene.image.Image;

/**
 * Represents the exit tile ('E') on the map.
 * This tile is passable. Chip must stand on this tile with all required
 * microchips to complete the level.
 */
public class ExitTile extends Tile {
     /**
     * Constructs a new ExitTile.
     * It is always initialized as passable.
     */
    public ExitTile(){
        super(true);
        image = new Image(getClass().getResourceAsStream("/ccprog3/chipschallenge/images/exit.png"));

    }

     /**
     * Gets the character symbol for this tile.
     *
     * @return Always returns 'E'.
     */
    @Override
    public char getSymbol(){
        return 'E';
    }

     /**
     * Defines Chip's interaction when standing on the exit tile.
     * It checks if Chip has collected all the required microchips
     * ({@link Chip#hasAllChips(int)}).
     * If Chip has all chips, it sets Chip's status to be at the exit
     * ({@link Chip#setAtExit(boolean)}), allowing the {@link gamefiles.Game}
     * to detect level completion.
     * If Chip does not have all chips, it prints a message informing
     * the player.
     *
     * @param chip The {@link Chip} instance interacting with the tile.
     * @param map  The {@link Map}, used to check the total number of chips needed.
     */
    @Override
    public void interact(Chip chip, Map map){
        if(chip.hasAllChips(map.getChipsNeeded())){
            chip.setAtExit(true);
        }
        else
            System.out.println("Insufficient Microchips.");
    }

    @Override
    public Image getImage() {
        return image;
    }
}
