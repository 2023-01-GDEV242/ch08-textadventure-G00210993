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
    private String itemDesc;
    private int itemWeight;

    /**
     * Create an Item named "itemDesc".
     * @param itemDesc The item's description.
     * @param itemWeight the item's weight.
     */
    public Item(String itemDesc, int itemWeight) 
    {
        this.itemDesc = itemDesc;
        this.itemWeight = itemWeight;
    }
    
    /**
     * @return itemDesc returns the description of item
     */
    public String getItemDesc() {
        return itemDesc;
    }
    
    /**
     * @return itemWeight returns the weight of item
     */
    public int getItemWeight() {
        return itemWeight;
    }
}
