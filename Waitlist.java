/**
 *author: Jiali Zhao 
 */
//package Pro1_Warehouse;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Waitlist {

    private List<WaitlistItem> items;

    public Waitlist() {
        items = new ArrayList<>();
    }

    /**
     * Add a new waitlist entry for a given client and product.
     * If the client is already on the waitlist for this product,
     * it increases the quantity instead of adding a duplicate entry.
     */
    public void addItem(String clientId, String productId, int quantity) {
        for (WaitlistItem item : items) {
            if (item.getClientId().equals(clientId) && item.getProductId().equals(productId)) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        items.add(new WaitlistItem(clientId, productId, quantity)); // .add() is a built-in method in java for list data type
    }

    /**
     * Returns all waitlist items.
     */
    public List<WaitlistItem> getItems() {
        return items;
    }

    /**
     * Returns true if there are no waiting clients.
     */
    public boolean isEmpty() {
        return items.isEmpty();
    }

    /**
     * Fulfill as many waitlist items as possible with the available stock.
     * Generates invoices for fully or partially filled items.
     * Returns remaining stock after filling the waitlist.
     */
    public int fulfillItems(int availableQty) {
        Iterator<WaitlistItem> iterator = items.iterator();

        while (iterator.hasNext() && availableQty > 0) {
            WaitlistItem item = iterator.next();
            int need = item.getQuantity();

            if (availableQty >= need) {
                // Full fill
                generateInvoice(item.getClientId(), item.getProductId(), need);
                availableQty -= need;
                iterator.remove();  // remove fulfilled item
            } else {
                // Partial fill
                generateInvoice(item.getClientId(), item.getProductId(), availableQty);
                item.setQuantity(need - availableQty);
                availableQty = 0;
            }
        }
        return availableQty; // any leftover stock
    }

    /**
     * Display the waitlist for this product.
     */
    public void printWaitlist() {
        if (items.isEmpty()) {
            System.out.println("  (No clients waiting)");
            return;
        }

        System.out.println("  Current Waitlist:");
        for (WaitlistItem item : items) {
            System.out.println("    Client ID: " + item.getClientId() +
                               ", Quantity: " + item.getQuantity() +
                               ", Date Added: " + item.getDateAdded());
        }
    }

    /**
     * Simulates generating an invoice.
     * In your real system, this should connect to your Invoice/Warehouse class.
     */
    private void generateInvoice(String clientId, String productId, int qty) {
        System.out.println("Invoice generated → Client: " + clientId +
                ", Product: " + productId + ", Quantity: " + qty);
    }
}



