/**
 *author: Jiali Zhao 
 */
//package Pro1_Warehouse;

import java.util.Date;

public class WaitlistItem {
    private String clientId;       // the ID of the client who’s waiting
    private String productId;      // the ID of the product being waited for
    private int quantity;          // number of units still needed
    private Date dateAdded;        // when the item was added to the waitlist
    private boolean fulfilled;     // optional flag once shipment fills it

    public WaitlistItem(String clientId, String productId, int quantity) {
        this.clientId = clientId;
        this.productId = productId;
        this.quantity = quantity;
        this.dateAdded = new Date();
        this.fulfilled = false;
    }

    public WaitlistItem(Client client, Product product, int quantity) {
        this(client.getId(), product.getID(), quantity);
    }


    public String getClientId() { 
        return clientId; 
    }

    public String getProductId() { 
        return productId; 
    }

    public int getQuantity() { 
        return quantity; 
    }
    
    public boolean isFulfilled() { 
        return fulfilled; 
    }

    public void setQuantity(int qty) { 
        this.quantity = qty; 
    }

    public void markFulfilled() { 
        this.fulfilled = true; 
    }

    public Date getDateAdded() {
        return dateAdded;
    }


    @Override
    public String toString() {
        return "WaitlistItem{" +
                "clientId='" + clientId + '\'' +
                ", productId='" + productId + '\'' +
                ", quantity=" + quantity +
                ", dateAdded=" + dateAdded +
                ", fulfilled=" + fulfilled +
                '}';
    }
}

