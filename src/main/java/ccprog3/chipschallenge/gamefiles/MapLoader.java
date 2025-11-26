package ccprog3.chipschallenge.gamefiles;

import ccprog3.chipschallenge.tiles.*;

/**
 * A factory class responsible for creating specific {@link Tile} objects
 * based on a character symbol.
 * <p>
 * This class provides a static utility method, {@link #createTile(char)},
 * which is used by {@link Map} to parse a level file and instantiate
 * the correct tile type for each character in the file.
 */
public class MapLoader{

     /**
     * Creates and returns a specific {@link Tile} subclass instance
     * corresponding to the given character symbol.
     * This method utilizes a switch expression to map file characters
     * to their respective tile objects (e.g., '#' becomes a {@link WallTile},
     * 'c' becomes a {@link BlankTile} containing a chip {@link Item}).
     *
     * @param ch The character symbol from the level file.
     * @return The corresponding {@link Tile} object. Returns a {@link BlankTile}
     * by default if the character is not recognized.
     */
    public static Tile createTile(char ch){

        return switch (ch) {
            case 'c', 'r', 'b', 'w', 'f', 'I', 'z' -> new BlankTile(new Item(ch));
            case '#' -> new WallTile();
            case '~' -> new WaterTile();
            case 'F' -> new FireTile();
            case 'B' -> new BlueDoor();
            case 'R' -> new RedDoor();
            case 'E' -> new ExitTile();
            case 'W', 'A', 'S', 'D' -> new SliderTile(ch);
            case '^' -> new ForceTile('W');
            case '<' -> new ForceTile('A');
            case 'v' -> new ForceTile('S');
            case '>' -> new ForceTile('D');
            case '8', '2', '4', '6' -> new EnemyMover(ch);
            case 'i' -> new IceTile();
            case 'a' -> new AcidTile();
            default -> new BlankTile();
        };
    }
}