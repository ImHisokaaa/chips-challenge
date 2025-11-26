package ccprog3.chipschallenge.gamefiles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;

import ccprog3.chipschallenge.tiles.Tile;

/**
 * Represents the game map (level).
 * This class is responsible for loading the level from a text file,
 * storing the grid of {@link Tile} objects, and tracking level-specific
 * data like the player's start position and the number of chips required.
 */
public class Map{

    private int chipsNeeded;
    private int width, height;
    private int startX, startY;
    private Tile[][] map;
    private ArrayList<Enemy> enemies;

     /**
     * Constructs a new Map by loading and parsing the specified level file.
     *
     * @param level The name of the level file (e.g., "level1.txt") to load.
     * @throws IOException If an error occurs during file reading (though the current
     * implementation in {@link #fillMap(String)} catches this).
     */
    public Map(String level) throws IOException{
        this.chipsNeeded = 0;
        enemies = new ArrayList<>();
        fillMap(level);
    }

     /**
     * Reads the specified level file, parses its contents, and populates the
     * 2D {@code map} array with corresponding {@link Tile} objects.
     * It also sets the map dimensions, chip count, and player start position.
     *
     * @param level The name of the level file to read.
     */
    public void fillMap(String level){
        ArrayList<String> rows = new ArrayList<>();

        InputStream is = Map.class.getResourceAsStream(level);
        if (is == null) {
            throw new RuntimeException("Level file not found: " + level);
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String row;
            while ((row = reader.readLine()) != null) {
                if (row.trim().isEmpty()) continue;
                rows.add(row);
            }
        } catch (IOException e) {
            System.err.println("Error reading level file: " + level);
        }


        this.width = rows.getFirst().length();
        this.height = rows.size();
        this.map = new Tile[height][width];

        for (int y = 0; y < this.height; y++)
            for (int x = 0; x < this.width; x++) {
                char ch = rows.get(y).charAt(x);
                this.map[y][x] = MapLoader.createTile(ch);
                if (ch == 'c')
                    this.chipsNeeded++;
                else if (ch == 'P'){
                    this.startX = x;
                    this.startY = y;
                }
                else if (ch == 'e'){
                    enemies.add(new Enemy(x,y, 'A'));

                }
                else if (ch == 't'){
                    enemies.add(new Enemy(x, y, 'D'));
                }
            }
    }


     /**
     * Renders the current state of the map to the console.
     * It draws the player's character ('P') at Chip's current location,
     * overriding the tile symbol that is actually at that position.
     *
     * @param chip The {@link Chip} object, used to get the player's current (x, y) coordinates.
     */
    public void displayMap(Chip chip){
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.height; x++) {
                char currSymbol = this.map[y][x].getSymbol();
                boolean hasEnemy = false;
                for (Enemy e : enemies){
                    if (e.getX() == x && e.getY() == y){
                        System.out.print('x');
                        hasEnemy = true;
                        break;
                    }
                }
                if (hasEnemy)
                    continue;

                if (chip.getX() == x && chip.getY() == y) // chips location
                    System.out.print('P');
                else
                    System.out.print(this.map[y][x].getSymbol());
            }
            System.out.println(); // new line
        }
    }

    /**
     * Sets a tile reference to a new tile reference.
     * Note: This method, as written, only reassigns the local parameter
     * {@code tile} and will not modify the tile in the {@code map} array.
     * To modify the map, use {@code setMapTile(int x, int y, Tile newTile)}.
     * (Assuming such a method existed, or by modifying {@link #getTileAt(int, int)}'s
     * returned reference if Tiles are mutable).
     *
     * @param tile    The original tile reference.
     * @param newTile The new tile reference to assign locally.
     */
    public void setMapTile(Tile tile, Tile newTile){
        tile = newTile;
    }

    /**
     * Gets the {@link Tile} object at the specified (x, y) coordinates.
     *
     * @param x The x-coordinate (column).
     * @param y The y-coordinate (row).
     * @return The {@link Tile} at that position.
     */
    public Tile getTileAt(int x, int y){return this.map[y][x];}

    /**
     * Gets the total number of microchips required to complete the level.
     *
     * @return The count of microchips.
     */
    public int getChipsNeeded(){return this.chipsNeeded;}

    /**
     * Gets the width (number of columns) of the map.
     *
     * @return The map width.
     */
    public int getWidth(){return this.width;}

     /**
     * Gets the height (number of rows) of the map.
     *
     * @return The map height.
     */
    public int getHeight(){return this.height;}

    /**
     * Gets the player's starting x-coordinate.
     *
     * @return The starting x-coordinate.
     */
    public int getStartX(){return this.startX;}

    /**
     * Gets the player's starting y-coordinate.
     *
     * @return The starting y-coordinate.
     */
    public int getStartY(){return this.startY;}

    public ArrayList<Enemy> getEnemies(){return this.enemies;}
}