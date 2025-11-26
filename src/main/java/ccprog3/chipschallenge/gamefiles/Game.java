package ccprog3.chipschallenge.gamefiles;

import java.io.IOException;
import java.util.Scanner;
import ccprog3.chipschallenge.tiles.DoorTile;
import ccprog3.chipschallenge.tiles.Tile;

 /**
 * Manages the main game logic, loop, and player interaction with the map.
 * This class connects the {@link Chip} (player) and the {@link Map} (level)
 * and controls the flow of the game from start to completion or game over.
 */
public class Game{
    private Chip chip;
    private Map map;
    private boolean completed;
    private int level;
    private boolean isRunning;
     /**
     * Constructs a new Game instance.
     *
     * @param map  The {@link Map} object representing the level.
     * @param chip The {@link Chip} object representing the player.
     */
    public Game(Map map, Chip chip, int level){
        this.map = map;
        this.chip = chip;
        this.completed = false;
        this.isRunning = true;
        this.level = level;
    }

    /**
     * Clears the console screen using ANSI escape codes.
     */
     public void clearScreen() {
         System.out.print("\033[H\033[2J");
         System.out.flush();
     }

     /**
     * Advances the game to the next level number.
     */
     private void nextLevel(){
        level++;
     }

     /**
     * Gets the current level number.
     *
     * @return The integer representing the level.
     */
     public int getLevel(){
        return level;
     }

     /**
     * Processes a single character movement input from the player.
     * Calculates the target coordinates based on the input.
     * It checks if the target {@link Tile} is passable.
     * If passable, Chip moves, and the new tile's method is called.
     * If it is a non-passable {@link DoorTile} (e.g., a locked door),
     * it triggers the interaction without moving, allowing Chip to attempt to unlock it.
     *
     * @param input The movement command character ('W', 'A', 'S', 'D').
     */
    public void charMove(char input){
        int nY = this.chip.getY();
        int nX = this.chip.getX();

        switch(input){
            case 'W': nY--; break;
            case 'A': nX--; break;
            case 'S': nY++; break;
            case 'D': nX++; break;
        }

        Tile checkTile = this.map.getTileAt(nX, nY);
        if (checkTile != null && checkTile.isPassable()){
            this.chip.move(input);
            this.map.getTileAt(this.chip.getX(), this.chip.getY()).interact(this.chip, this.map);
        }
        if(checkTile instanceof DoorTile dTile && !dTile.isPassable()){ // if there is a locked door
            this.map.getTileAt(nX,nY).interact(this.chip, this.map);
        }

    }

     /**
     * Checks if the level's completion criteria are met.
     * The level is complete if Chip has collected all required microchips
     * and is currently standing on the exit tile.
     * If criteria are met, the {@code completed} flag is set to true.
     */
    public boolean checkIfComplete(){
        if(this.chip.hasAllChips(this.map.getChipsNeeded()) && this.chip.isAtExit()){
            nextLevel();
            return true;
        }
        return false;
    }

    /**
     * Runs the main game loop.
     * 
     * The loop continues as long as {@link Chip#isAlive()} is true and
     * the {@code completed} flag is false.
     * 
     * In each iteration, it:
     * 1. Displays the current map state.
     * 2. Reads the next character input from the console.
     * 3. Processes the player's move.
     * 4. Prints the player's inventory status.
     * 5. Checks if the level completion criteria have been met.
     * 
     * After the loop terminates, it prints a completion message if the level was won.
     *
     * @throws IOException If an I/O error occurs (as per the method signature).
     */
    public void gameLoop() throws IOException {
        Scanner sc = new Scanner(System.in);
        Thread enemyThread = new Thread(() -> {
            while (isRunning && chip.isAlive()) {
                for (Enemy e : map.getEnemies()) {
                    e.move(map, chip);
                }
                clearScreen();
                map.displayMap(chip);
                chip.getInventory().printInventoryStatus();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {}
            }
        });
        enemyThread.start();

        while (!completed && chip.isAlive()) {
            char input = sc.nextLine().toUpperCase().charAt(0);
            input = Character.toUpperCase(input);
            charMove(input);
            clearScreen();
            map.displayMap(chip);
            chip.getInventory().printInventoryStatus();

            checkIfComplete();

        }

        isRunning = false; // stop enemy thread

        if (completed) {
            System.out.println("🎉 You have completed the level!");
        }
    }

}