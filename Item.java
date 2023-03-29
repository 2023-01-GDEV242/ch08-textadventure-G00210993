/**
 * Class Item - an item in an adventure game.  
 *
 * An "Item" represents one holdable 
 * 
 * a in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Michael B
 * @version 2023.3.28
 */

public class Item 
{
    private String itemName;
    private String itemDescription;
    private int itemWeight;

    /**
     * Create an Item described "itemName".
     * @param itemName The item's name.
     * @param itemWeight the item's weight.
     */
    public Item(String itemName, String itemDescription, int itemWeight) 
    {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemWeight = itemWeight;
    }
}
