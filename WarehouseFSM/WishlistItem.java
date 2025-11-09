/**
 * @author Bryan Erickson
 * The class file for a wishlist item
 */
public class WishlistItem {

    private final String itemId;
    private final String itemName;
    private final int desiredQuantity;

    public WishlistItem(String id, String name, int quantity) {
        this.itemId = id;
        this.itemName = name;
        this.desiredQuantity = quantity;
    }

    @Override
    public String toString() {
        return itemId + " " + itemName + " " + desiredQuantity;
    }

    public String getItemID() {
        return this.itemId;
    }

    public String getItemName() {
        return this.itemName;
    }
       public int getQuantity() {
        return this.desiredQuantity;
    }

    public static void main(String[] args) {

    }
}
