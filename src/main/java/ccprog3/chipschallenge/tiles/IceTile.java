package ccprog3.chipschallenge.tiles;

import ccprog3.chipschallenge.gamefiles.Chip;
import ccprog3.chipschallenge.gamefiles.Map;
import javafx.scene.image.Image;

/**
 * Represents a slippery ice tile.
 * Players without Ice Boots (skates) will slide uncontrollably across these tiles
 * until they hit a wall or a different type of floor.
 */
public class IceTile extends Tile{

    /**
     * Constructs a new IceTile.
     * Sets the tile as passable and loads the ice image.
     */
    public IceTile(){
        super(true);
        image = new Image(getClass().getResourceAsStream("/ccprog3/chipschallenge/images/ice.png"));
    }

    /**
     * Helper logic to slide the player continuously in a specific direction.
     * The slide continues until the player hits a wall, a non-passable object,
     * or lands on a different tile type. It also handles chaining slides (e.g., hitting a force floor).
     *
     * @param chip The player character being slid.
     * @param map  The game map to check for collisions.
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
     * Handles interaction when Chip enters this tile.
     * Checks if the player has Ice Boots. If not, triggers the sliding mechanism.
     *
     * @param chip The player character.
     * @param map  The current game map.
     */
    @Override
    public void interact(Chip chip, Map map) {
        if(!chip.getInventory().hasIceBoots())
            slideChip(chip, map, chip.getDir());
    }

    /**
     * Gets the symbol representing this tile.
     *
     * @return The character 'I'.
     */
    @Override
    public char getSymbol(){
        return 'I';
    }

    /**
     * Retrieves the visual image for this tile.
     *
     * @return The Image object.
     */
    @Override
    public Image getImage() {return image;}
}
