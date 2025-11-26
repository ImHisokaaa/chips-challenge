package ccprog3.chipschallenge.gamefiles;
import ccprog3.chipschallenge.tiles.Tile;
import ccprog3.chipschallenge.tiles.EnemyMover;
import javafx.scene.image.Image;

/**
 * Represents an enemy character in the game.
 * Enemies move automatically, bounce off walls, and kill the player on contact.
 */
public class Enemy {

    private int x, y;
    private int dx, dy;
    private char dir;

    /**
     * Constructs a new Enemy at the specified starting coordinates.
     * Sets the initial movement vector based on the provided direction.
     *
     * @param iniX The starting x-coordinate.
     * @param iniY The starting y-coordinate.
     * @param dir  The initial direction ('A' sets positive x movement, 'D' sets negative).
     */
    public Enemy(int iniX, int iniY, char dir){

        this.x = iniX;
        this.y = iniY;
        this.dir = dir;
        if (dir == 'A') {
            this.dx = 1;
            this.dy = 0;
        }
        else if (dir == 'D'){
            this.dx = -1;
            this.dy = 0;
        }

    }

    /**
     * Updates the enemy's position for the current turn.
     * The enemy bounces off walls, interacts with special tiles, and kills Chip if they collide.
     *
     * @param map  The game map to check for walls and tiles.
     * @param chip The player character to check for collisions.
     */
    public void move(Map map, Chip chip){

        int newX = x + dx;
        int newY = y + dy;

        Tile nextTile = map.getTileAt(newX, newY);

        // if hits unpassable tile, change direction
        if (nextTile == null || !nextTile.isPassable()) {
            dx = -dx;
            dy = -dy;
            newX = x + dx;
            newY = y + dy;
        }

        // move enemy
        this.x = newX;
        this.y = newY;

        if (nextTile instanceof EnemyMover mover){
            changeDirection(mover.getDirection());
        }

        if (x == chip.getX() && y == chip.getY()){
            chip.die("Was Killed. :((");
        }
    }

    /**
     * Forces the enemy to change its movement direction using Numpad-style codes.
     * '8'=Up, '4'=Left, '2'=Down, '6'=Right.
     *
     * @param dir A character representing the new direction.
     */
    public void changeDirection(char dir){
        switch(dir){
            case '8': dx = 0; dy = -1; this.dir = 'W'; break;
            case '4': dx = -1; dy = 0; this.dir = 'A'; break;
            case '2': dx = 0; dy = 1;  this.dir = 'S'; break ;
            case '6': dx = 1; dy = 0;  this.dir = 'D'; break;
        }
    }

    /**
     * Gets the enemy's current x-coordinate.
     *
     * @return The x-coordinate.
     */
    public int getX() {return x;}

    /**
     * Gets the enemy's current y-coordinate.
     *
     * @return The y-coordinate.
     */
    public int getY() {return y;}

    /**
     * Gets the visual sprite for the enemy based on its current facing direction.
     *
     * @return An Image object of the enemy.
     */
    public Image getImage() {
        return switch (dir){
            case 'W' -> new Image(getClass().getResourceAsStream("/ccprog3/chipschallenge/images/enemyUp.png"));
            case 'A' -> new Image(getClass().getResourceAsStream("/ccprog3/chipschallenge/images/enemyLeft.png"));
            case 'S' -> new Image(getClass().getResourceAsStream("/ccprog3/chipschallenge/images/enemyDown.png"));
            case 'D' -> new Image(getClass().getResourceAsStream("/ccprog3/chipschallenge/images/enemyRight.png"));
            default -> new Image(getClass().getResourceAsStream("/ccprog3/chipschallenge/images/enemyUp.png"));
        };
    }
}
