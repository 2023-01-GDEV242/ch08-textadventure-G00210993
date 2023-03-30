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
    private String itemDesc;
    private int itemWeight;

    /**
     * Create an Item named "itemName".
     * @param itemName The item's name.
     * @param itemDesc The item's description.
     * @param itemWeight the item's weight.
     */
    public Item(String itemName, String itemDesc, int itemWeight) 
    {
        this.itemName = itemName;
        this.itemDesc = itemDesc;
        this.itemWeight = itemWeight;
    }
    
    /**
     * 
     * @return
     */
    public String getItemName() {
        return itemName;
    }
    
    /**
     * 
     */
    public String getItemDesc() {
        return itemDesc;
    }
    
    /**
     * 
     */
    public int getItemWeight() {
        return itemWeight;
    }
}
