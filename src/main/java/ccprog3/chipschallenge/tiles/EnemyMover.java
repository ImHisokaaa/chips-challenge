package ccprog3.chipschallenge.tiles;
import ccprog3.chipschallenge.gamefiles.Chip;
import ccprog3.chipschallenge.gamefiles.Map;
import javafx.scene.image.Image;

public class EnemyMover extends Tile{

    private char direction; // 8 - up, 4 - left, 2 - down, 6 right

    public EnemyMover(char dir){
        super(true );
        this.direction = dir;
        image = new Image(getClass().getResourceAsStream("/ccprog3/chipschallenge/images/blank.png"));

    }

    @Override
    public char getSymbol(){return '_';}

    public char getDirection(){return this.direction;}

    @Override
    public void interact(Chip chip, Map map){

    }

    @Override
    public Image getImage() {
        return image;
    }
}
