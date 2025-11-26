package ccprog3.chipschallenge.gamefiles;

/**
 * Represents the inventory of the player character (Chip).
 * This class tracks the count of collected items such as keys and microchips,
 * as well as special gear like fire boots and flippers.
 */
public class Inventory{
    private int microchips, redKeys, blueKeys;
    private boolean hasFireBoots, hasFlippers, hasIceBoots, hasAcidBoots;

    /**
     * Constructs a new, empty Inventory.
     * All item counts are initialized to 0, and special gear flags
     * (fire boots, flippers) are initialized to {@code false}.
     */
    public Inventory(){
        this.microchips = 0;
        this.redKeys = 0;
        this.blueKeys = 0;
        this.hasFireBoots = false;
        this.hasFlippers = false;
    }

     /**
     * Checks if the inventory contains flippers.
     *
     * @return {@code true} if Chip has flippers, {@code false} otherwise.
     */
    public boolean hasFlippers(){return this.hasFlippers;}

     /**
     * Checks if the inventory contains fire boots.
     *
     * @return {@code true} if Chip has fire boots, {@code false} otherwise.
     */
    public boolean hasFireBoots(){return this.hasFireBoots;}

     /**
     * Gets the current count of microchips.
     *
     * @return The number of microchips in the inventory.
     */
    public int getMicrochips(){return this.microchips;}

     /**
     * Gets the current count of red keys.
     *
     * @return The number of red keys in the inventory.
     */
    public int getRedKeys(){return this.redKeys;}

     /**
     * Gets the current count of blue keys.
     *
     * @return The number of blue keys in the inventory.
     */
    public int getBlueKeys(){return this.blueKeys;}

    /**
     * Checks if the inventory contains ice boots (skates).
     *
     * @return true if Chip has ice boots, false otherwise.
     */
    public boolean hasIceBoots() {return hasIceBoots;}

    /**
     * Checks if the inventory contains acid boots (suction shoes).
     *
     * @return true if Chip has acid boots, false otherwise.
     */
    public boolean hasAcidBoots() {return hasAcidBoots;}

    /**
     * Attempts to use one red key.
     * If one or more red keys are available, decrements the count by one
     * and returns {@code true}.
     *
     * @return {@code true} if a key was used, {@code false} if no red keys were available.
     */
    public boolean useRed(){
        if (this.redKeys != 0) {
            this.redKeys--;
            return true;
        }
        return false;
    }

     /**
     * Attempts to use one blue key.
     * If one or more blue keys are available, decrements the count by one
     * and returns {@code true}.
     *
     * @return {@code true} if a key was used, {@code false} if no blue keys were available.
     */
    public boolean useBlue(){
        if (this.blueKeys != 0) {
            this.blueKeys--;
            return true;
        }
        return false;
    }

     /**
     * Adds one red key to the inventory.
     */
    public void addRed(){
            this.redKeys++;
    }

     /**
     * Adds one blue key to the inventory.
     */
    public void addBlue(){
            this.blueKeys++;
    }

     /**
     * Adds one microchip to the inventory.
     */
    public void addMicrochip(){
        this.microchips++;
    }

     /**
      * Adds fire boots to the inventory, setting the flag to {@code true}.
      */
    public void addFireBoots(){
        this.hasFireBoots = true;
    }

     /**
      * Adds flippers to the inventory, setting the flag to {@code true}.
      */
    public void addFlippers(){
        this.hasFlippers = true;
    }

    /**
     * Equips the player with ice boots (skates).
     */
    public void addIceShoes(){this.hasIceBoots = true;}

    /**
     * Equips the player with acid boots (suction shoes).
     */
    public void addAcidShoes(){this.hasAcidBoots = true;}

     /**
     * Prints the current status of the inventory (keys and microchips)
     * to the console, formatted for readability.
     */
    public void printInventoryStatus() {
        System.out.println("-----------------------------");
        System.out.println("Inventory Status:");
        System.out.println("  Red Keys  : " + redKeys);
        System.out.println("  Blue Keys : " + blueKeys);
        System.out.println("  Microchips: " + microchips);
        System.out.println("-----------------------------");
    }

}