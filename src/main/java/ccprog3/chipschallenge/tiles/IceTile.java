package ccprog3.chipschallenge.tiles;

import ccprog3.chipschallenge.gamefiles.Chip;
import ccprog3.chipschallenge.gamefiles.Map;
import javafx.scene.image.Image;

public class IceTile extends Tile{

    public IceTile(){
        super(true);
        image = new Image(getClass().getResourceAsStream("/ccprog3/chipschallenge/images/ice.png"));
    }

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
    @Override
    public void interact(Chip chip, Map map) {
        if(!chip.getInventory().hasIceBoots())
            slideChip(chip, map, chip.getDir());
    }

    @Override
    public char getSymbol(){
        return 'I';
    }

    @Override
    public Image getImage() {return image;}
}
