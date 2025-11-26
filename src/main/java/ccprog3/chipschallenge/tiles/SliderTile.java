package ccprog3.chipschallenge.tiles;

import ccprog3.chipschallenge.gamefiles.Chip;
import ccprog3.chipschallenge.gamefiles.Map;
import javafx.scene.image.Image;

/**
 * Represents a "slider" tile, often analogous to an ice tile.
 * When Chip steps on this tile, he is forced to slide in a specific
 * direction until hitting a non-passable tile or another interactive tile.
 * This tile is distinct from {@link ForceTile} as its interaction logic
 * differs when encountering other tiles (e.g., it stops on most tiles
 * but chains with other sliders and triggers ForceTiles).
 * Note: The class name "SliderTile" is used, but the in-code symbols
 * ('W', 'A', 'S', 'D') suggest it might also represent monsters/sliders.
 * The logic implements sliding movement.
 */
public class SliderTile extends Tile{
    private char direction;

     /**
     * Constructs a new SliderTile.
     *
     * @param direction The direction ('W', 'A', 'S', 'D') this tile will force movement.
     */
    public SliderTile(char direction){
        super(true);
        this.direction = direction;
    }

     /**
     * Handles the logic for continuously sliding Chip in a given direction.
     * The slide continues in a loop until:
     * 1. The next tile is null or not passable ({@link Tile#isPassable()}).
     * 2. Chip lands on another {@link SliderTile}, updating the slide direction.
     * 3. Chip lands on a {@link ForceTile}, triggering that tile's interaction.
     * 4. Chip lands on any other tile (e.g., {@link BlankTile}), stopping the slide
     * and triggering that tile's interaction.
     *
     * @param chip The {@link Chip} instance being pushed.
     * @param map  The {@link Map} used for navigation and tile checking.
     * @param dir  The current direction of the slide.
     */
    private void slideChip(Chip chip, Map map, char dir){
        boolean isSliding = true;

        while(isSliding){
            int nY = chip.getY();
            int nX = chip.getX();

            switch(dir){
                case 'W': nY--; break;
                case 'A': nX--; break;
                case 'S': nY++; break;
                case 'D': nX++; break;
            }
            Tile checkTile = map.getTileAt(nX, nY);

            if (checkTile == null || !checkTile.isPassable()){
                isSliding = false;
            }
            else{

                chip.move(dir); // move chip
                map.displayMap(chip);

                if (map.getTileAt(chip.getX(), chip.getY()) instanceof SliderTile sTile){ // if tile slid to is another sliderTile
                    dir = sTile.getDirection();
                }
                else if (map.getTileAt(chip.getX(), chip.getY()) instanceof ForceTile fTile){ // if tile slid to is a forceTile
                    fTile.interact(chip,map);
                }
                else {
                    isSliding = false;
                    map.getTileAt(chip.getX(), chip.getY()).interact(chip, map);
                }
            }
        }
    }

     /**
     * Gets the movement direction of this slider tile.
     *
     * @return The direction character ('W', 'A', 'S', 'D').
     */
    public char getDirection(){return this.direction;}

     /**
     * Defines Chip's interaction upon entering the slider tile.
     * Triggers the {@link #slideChip(Chip, Map, char)} method to start the
     * forced sliding movement.
     *
     * @param chip The {@link Chip} instance interacting with the tile.
     * @param map  The {@link Map} used to check subsequent tiles during the slide.
     */
    @Override
    public void interact(Chip chip, Map map){
        slideChip(chip, map, this.direction);
    }

     /**
     * Gets the character symbol representing this slider tile.
     * The symbol directly corresponds to its movement direction.
     *
     * @return The direction character ('W', 'A', 'S', 'D').
     */
    @Override
    public char getSymbol(){
        return this.direction;
    }

    /**
     * Retrieves the visual sprite for this tile based on its direction.
     * Maps the direction characters ('W', 'A', 'S', 'D') to the corresponding arrow images.
     *
     * @return The Image object representing the directional force floor.
     */
    @Override
    public Image getImage() {
        return switch (direction){
            case 'W' -> new Image(getClass().getResourceAsStream("/ccprog3/chipschallenge/images/slideUp.png"));
            case 'A' -> new Image(getClass().getResourceAsStream("/ccprog3/chipschallenge/images/slideLeft.png"));
            case 'S' -> new Image(getClass().getResourceAsStream("/ccprog3/chipschallenge/images/slideDown.png"));
            case 'D' -> new Image(getClass().getResourceAsStream("/ccprog3/chipschallenge/images/slideRight.png"));
            default -> new Image(getClass().getResourceAsStream("/ccprog3/chipschallenge/images/blank.png"));
        };
    }
}
