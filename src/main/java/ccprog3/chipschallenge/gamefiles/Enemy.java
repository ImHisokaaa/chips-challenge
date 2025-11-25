package ccprog3.chipschallenge.gamefiles;
import ccprog3.chipschallenge.tiles.Tile;
import ccprog3.chipschallenge.tiles.EnemyMover;
import javafx.scene.image.Image;

public class Enemy {

    private int x, y;
    private int dx, dy;
    private char dir;
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

    public void changeDirection(char dir){
        switch(dir){
            case '8': dx = 0; dy = -1; this.dir = 'W'; break;
            case '4': dx = -1; dy = 0; this.dir = 'A'; break;
            case '2': dx = 0; dy = 1;  this.dir = 'S'; break ;
            case '6': dx = 1; dy = 0;  this.dir = 'D'; break;
        }
    }

    public int getX() {return x;}
    public int getY() {return y;}

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
