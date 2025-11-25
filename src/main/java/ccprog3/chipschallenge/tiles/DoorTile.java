package ccprog3.chipschallenge.tiles;

/**
 * Represents an abstract base class for all door tiles.
 * Doors are specialized tiles that are typically not passable
 * until some condition (like possessing a key) is met.
 * This class manages the locked state and the door's type (color).
 * Subclasses like {@link BlueDoor} or {@link RedDoor} will implement
 * the specific interaction logic.
 */
public abstract class DoorTile extends Tile {
    protected String color;
    protected boolean isLocked;

     /**
     * Constructs a new DoorTile.
     * By default, a door is initialized as <b>not passable</b>.
     *
     * @param color A string identifying the door type, typically its color.
     */
    public DoorTile(String color){
        super(false);
        this.color = color;
    }
}
