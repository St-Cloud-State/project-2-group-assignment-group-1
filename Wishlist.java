
/**
 * @author Bryan Erickson
 *
 * Wishlist class file
 *
 */

import java.util.ArrayList;
import java.util.Iterator;
public class Wishlist {

    private ArrayList<WishlistItem> wantedItems = new ArrayList<>();
    private final int clientID;

    public Wishlist(int idNum, ArrayList<WishlistItem> list) {
        this.clientID = idNum;
        this.wantedItems = list;
    }

    public boolean insertItem(WishlistItem newItem) {
        this.wantedItems.add(newItem);
        return true;
    }

    public ArrayList<WishlistItem> getList() {
        return this.wantedItems;
    }

    public int getClientID() {
        return this.clientID;
    }
    public Iterator getWishlist() {
    return wantedItems.iterator();
  }

    public void Print_Wishlist() {
        Iterator<WishlistItem> wish = getWishlist(); 
        while (wish.hasNext()){
            System.out.println(wish.next());
        }
    }

    public static void wishlist(String[] args) {
      

    }
}
