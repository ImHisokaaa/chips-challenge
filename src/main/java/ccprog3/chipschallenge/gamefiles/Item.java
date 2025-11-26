package ccprog3.chipschallenge.gamefiles;

import javafx.scene.image.Image;

/**
 * Represents a collectible item within the game.
 * This class stores the item's identity via a specific character symbol.
 * <p>
 * Symbols correspond to specific items (e.g., 'r' for red key, 'c' for microchip).
 * The {@link Chip#pickup(Item)} method interprets this symbol.
 */
public class Item{
    private char symbol;

     /**
     * Constructs a new Item with the specified symbol.
     *
     * @param symbol The character representing this item's type.
     */
    public Item(char symbol){
        this.symbol = symbol;
    }

     /**
     * Gets the symbol associated with this item.
     *
     * @return The character symbol of the item.
     */
    public char getSymbol(){
        return this.symbol;
    }

    public Image getImage(){
        return switch (this.symbol){
            case 'r' -> new Image(getClass().getResourceAsStream("/ccprog3/chipschallenge/images/redKey.png"));
            case 'f' -> new Image(getClass().getResourceAsStream("/ccprog3/chipschallenge/images/fireShoe.png"));
            case 'w' -> new Image(getClass().getResourceAsStream("/ccprog3/chipschallenge/images/flipper.png"));
            case 'c' -> new Image(getClass().getResourceAsStream("/ccprog3/chipschallenge/images/microchip.png"));
            case 'b' -> new Image(getClass().getResourceAsStream("/ccprog3/chipschallenge/images/blueKey.png"));
            case 'I' -> new Image(getClass().getResourceAsStream("/ccprog3/chipschallenge/images/iceShoes.png"));
            case 'z' -> new Image(getClass().getResourceAsStream("/ccprog3/chipschallenge/images/acidShoes.png"));
            default -> new Image(getClass().getResourceAsStream("/ccprog3/chipschallenge/images/blank.png"));
        };
    }
}