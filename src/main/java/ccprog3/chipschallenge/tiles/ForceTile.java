package ccprog3.chipschallenge.tiles;

import ccprog3.chipschallenge.gamefiles.Chip;
import ccprog3.chipschallenge.gamefiles.Map;
import javafx.scene.image.Image;

/**
 * Represents a force floor tile ('^', '<', 'v', '>').
 * When Chip steps on this tile, he is uncontrollably pushed in a
 * specified direction until he hits a non-passable tile or another
 * special interaction tile (like Water or Fire).
 */
public class ForceTile extends Tile{

    private char direction;

     /**
     * Constructs a new ForceTile.
     *
     * @param direction The direction ('W', 'A', 'S', 'D') this tile will push Chip.
     */
    public ForceTile(char direction){
        super(true);
        this.direction = direction;
    }

     /**
     * Defines Chip's interaction upon entering the force tile.
     * Triggers the {@link #pushChip(Chip, Map, char)} method to start the
     * forced movement.
     *
     * @param chip The {@link Chip} instance interacting with the tile.
     * @param map  The {@link Map} used to check subsequent tiles during the push.
     */
    @Override
    public void interact(Chip chip, Map map) {
        pushChip(chip, map, this.direction);
    }

     /**
     * Gets the direction of this force tile.
     *
     * @return The direction character ('W', 'A', 'S', 'D').
     */
    public char getDirection(){return this.direction;}

     /**
     * Handles the logic for continuously pushing Chip in a given direction.
     * The push continues in a loop until:
     * 1. The next tile is null or not passable ({@link Tile#isPassable()}).
     * 2. Chip lands on a {@link WaterTile} or {@link FireTile}, at which point the
     * push stops, and the interaction for that tile is triggered.
     * If Chip is pushed onto another {@link ForceTile}, the push direction is
     * updated, and the loop continues.
     *
     * @param chip The {@link Chip} instance being pushed.
     * @param map  The {@link Map} used for navigation and tile checking.
     * @param dir  The current direction of the push.
     */
    private void pushChip(Chip chip, Map map, char dir){
        boolean isPushing = true;

        while (isPushing){
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
                isPushing = false;
            }
            else{

                chip.move(dir);
                map.displayMap(chip);

                if (map.getTileAt(chip.getX(), chip.getY())instanceof ForceTile fTile){ // if forced to another Force Tile
                    dir = fTile.getDirection();
                }
                else if (map.getTileAt(chip.getX(), chip.getY())instanceof WaterTile ||
                        map.getTileAt(chip.getX(), chip.getY())instanceof FireTile){ // stop push at fire or water tiles, then interact
                    isPushing = false;
                    map.getTileAt(chip.getX(), chip.getY()).interact(chip, map);
                }
                else {
                    map.getTileAt(chip.getX(), chip.getY()).interact(chip, map);
                }
            }
        }
    }

     /**
     * Gets the character symbol representing this force tile's direction.
     *
     * @return '^' (W), '<' (A), 'v' (S), '>' (D), or '/' as a default.
     */
    @Override
    public char getSymbol(){
        return switch(this.direction){
            case 'W' -> '^';
            case 'A' -> '<';
            case 'S' -> 'v';
            case 'D' -> '>';
            default -> '/';
        };
    }

    @Override
    public Image getImage() {
        return switch (direction){
            case 'W' -> new Image(getClass().getResourceAsStream("/ccprog3/chipschallenge/images/forceUp.png"));
            case 'A' -> new Image(getClass().getResourceAsStream("/ccprog3/chipschallenge/images/forceLeft.png"));
            case 'S' -> new Image(getClass().getResourceAsStream("/ccprog3/chipschallenge/images/forceDown.png"));
            case 'D' -> new Image(getClass().getResourceAsStream("/ccprog3/chipschallenge/images/forceRight.png"));
            default -> new Image(getClass().getResourceAsStream("/ccprog3/chipschallenge/images/blank.png"));

        };
    }
}
