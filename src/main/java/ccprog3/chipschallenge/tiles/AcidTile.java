package ccprog3.chipschallenge.tiles;

import ccprog3.chipschallenge.gamefiles.Chip;
import ccprog3.chipschallenge.gamefiles.Map;
import javafx.scene.image.Image;

public class AcidTile extends Tile{

    public AcidTile(){
        super(true);
        image = new Image(getClass().getResourceAsStream("/ccprog3/chipschallenge/images/acid.png"));
    }

    @Override
    public char getSymbol(){
        return 'i';
    }

    @Override
    public Image getImage(){
        return this.image;
    }

    @Override
    public void interact(Chip chip, Map map){
        if(!chip.getInventory().hasAcidBoots())
            chip.die("Chip has Been Poisoned.");
    }
}
