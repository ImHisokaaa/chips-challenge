package ccprog3.chipschallenge.gamefiles;

import javafx.scene.image.Image;

/**
 * Represents Chip, the player character in the game.
 * This class manages Chip's position, inventory, and status (alive, at exit).
 */
public class Chip{

    private int x,y;
    private Inventory inventory;
    private boolean alive;
    private boolean atExit;
    private char dir;
    /**
    * Constructs a new Chip character at the specified starting coordinates.
    * Initializes Chip as alive, not at the exit, and with an empty inventory.
    *
    * @param x The starting x-coordinate for Chip.
    * @param y The starting y-coordinate for Chip.
    */
    public Chip(int x, int y){
        inventory = new Inventory();
        this.alive = true;
        this.atExit = false;
        this.x = x;
        this.y = y;
        this.dir = 'W';
    }

     /**
     * Moves Chip one tile in the specified direction.
     * 'W' moves up (decrements y), 'A' moves left (decrements x),
     * 'S' moves down (increments y), 'D' moves right (increments x).
     *
     * @param dir A character representing the direction of movement ('W', 'A', 'S', 'D').
     */
    public void move(char dir){
        this.dir = dir;
        switch(dir){
            case 'W': this.y--; break;
            case 'A': this.x--; break;
            case 'S': this.y++; break;
            case 'D': this.x++; break;
        }
    }

     /**
     * Adds the specified item to Chip's inventory.
     * The item added depends on the symbol of the {@link Item} object.
     * 'r' = Red Key, 'b' = Blue Key, 'c' = Microchip,
     * 'w' = Flippers, 'f' = Fire Boots.
     *
     * @param item The {@link Item} object to be picked up.
     */
    public void pickup(Item item){
        switch(item.getSymbol()){
            case 'r': this.inventory.addRed(); break;  // red key
            case 'b': this.inventory.addBlue(); break; // blue key
            case 'c': this.inventory.addMicrochip(); break; // microchip
            case 'w': this.inventory.addFlippers(); break; // flippers
            case 'f': this.inventory.addFireBoots(); break;// fire boots
            case 'I': this.inventory.addIceShoes(); break;
            case 'z': this.inventory.addAcidShoes(); break;
        }
    }

     /**
     * Checks if Chip has collected all the required microchips for the level.
     *
     * @param totalChips The total number of microchips present in the level.
     * @return {@code true} if Chip's microchip count equals {@code totalChips}, {@code false} otherwise.
     */
    public boolean hasAllChips(int totalChips){
        return this.inventory.getMicrochips() == totalChips;
    }

     /**
     * Kills Chip, setting his status to no longer alive.
     * Prints the cause of death to the console.
     *
     * @param cause A string describing the reason for Chip's death.
     */
    public void die(String cause){
        this.alive = false;
        System.out.println(cause);
    }

     /**
     * Gets Chip's inventory.
     *
     * @return The {@link Inventory} object belonging to Chip.
     */
    public Inventory getInventory(){return this.inventory;}

    /**
     * Gets Chip's current x-coordinate.
     *
     * @return The current x-coordinate.
     */
    public int getX(){return this.x;}

    /**
     * Gets Chip's current y-coordinate.
     *
     * @return The current y-coordinate.
     */
    public int getY(){return this.y;}

     /**
     * Checks if Chip is currently alive.
     *
     * @return {@code true} if Chip is alive, {@code false} otherwise.
     */
    public boolean isAlive(){return this.alive;}

     /**
     * Checks if Chip is currently on the exit tile.
     *
     * @return {@code true} if Chip is at the exit, {@code false} otherwise.
     */
    public boolean isAtExit(){return this.atExit;}

     /**
     * Sets Chip's status regarding being at the exit.
     *
     * @param bool {@code true} to indicate Chip is at the exit, {@code false} otherwise.
     */
    public void setAtExit(boolean bool){
        this.atExit = bool;
    }

    /**
     * Gets the visual sprite for Chip based on the direction he is facing.
     *
     * @return An Image object of Chip facing the current direction.
     */
    public Image getImage() {
        return switch (dir){
            case 'W' -> new Image(getClass().getResourceAsStream("/ccprog3/chipschallenge/images/chipUp.png"));
            case 'A' -> new Image(getClass().getResourceAsStream("/ccprog3/chipschallenge/images/chipLeft.png"));
            case 'S' -> new Image(getClass().getResourceAsStream("/ccprog3/chipschallenge/images/chipDown.png"));
            case 'D' -> new Image(getClass().getResourceAsStream("/ccprog3/chipschallenge/images/chipRight.png"));
            default -> new Image(getClass().getResourceAsStream("/ccprog3/chipschallenge/images/chipUp.png"));
        };
    }

    /**
     * Gets the direction Chip is currently facing.
     *
     * @return A character ('W', 'A', 'S', or 'D') representing direction.
     */
    public char getDir() {return dir;}
}