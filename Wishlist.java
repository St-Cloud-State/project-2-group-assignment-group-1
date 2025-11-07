
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

    public Wishlist(){
        this(0, new ArrayList<>());
    }

    public boolean insertItem(WishlistItem newItem) {
        this.wantedItems.add(newItem);
        return true;
    }

    public void processWishlist(){
        Client clientToProcess = c_list.getClient(clientID);
             if (wishlist.isEmpty()) {
                        System.out.println("break1");
                        break;

                    }
     for (WishlistItem item : wishlist) {

        Iterator<WishlistItem> iterator = wantedItems.iterator();
        while (iterator.hasNext()) {
            WishlistItem item = iterator.next();
            Product product = p_list.search(item.getItemName());
            
            if (product == null) {
                System.out.println("Product not found: " + item.getItemName());
                continue;
            }
            
         if (product.getStock() >= qty) {
                            product.setStock(product.getStock() - qty);
                            clientToProcess.addToBalance((float) (-totalCost));
                        } else {
                            int available = (int) product.getStock();
                            int waitQty = qty - available;

                            if (available > 0) {
                                product.setStock(0);
                                clientToProcess.addToBalance((float) (-(available * product.getPrice())));
                            }
                            Waitlist waitlist_temp = product.getWaitlist();
                            waitlist_temp.addItem(clientToProcess.getId(), product.getID(), waitQty);
                        }
                    }

                    wishlist.clear();
                    System.out.println("Wishlist processed");
                    break;


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

    public ArrayList<WishlistItem> getItems(){
        return wantedItems;
    }

    public static void wishlist(String[] args) {
      

    }
}
