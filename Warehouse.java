/**
 * @author Bryan Erickson, Jiali Zhao
 */
//package Pro1_Warehouse;

import java.io.*;
import java.util.*;

public class Warehouse {
    private static Warehouse warehouse; // singleton instance

    private ProductList productList;
    private ClientList clientList;

    // Private constructor for singleton
    private Warehouse() {
        /* productList = ProductList.instance();
        clientList = ClientList.instance(); */
    }

    // Singleton accessor
    public static Warehouse instance() {
        if (warehouse == null) {
            warehouse = new Warehouse();
        }
        return warehouse;
    }

    // ---------------- Core Functionalities ----------------

    /** Add a new product to the warehouse */
    public void insertProduct(String name, double quantity, double price) {
        productList.addProduct(name, quantity, price);
    }

    /** Add a new client to the warehouse */
    public boolean insertClient(String name, String id) {
        Client client = new Client(name, id, 50);
        if (clientList.addClient(client)) {
            return true;
        }
        return false;
    }
//needed maybe verify later
public ProductList getProductList(){
	return productList;
}

public Client searchClient(String id){
    
    return clientList.getClient(id);
}

public ClientList getClientList(){
	return clientList;
}
    /** Process an order from a client */
    public void processOrder(String clientId, String productId, int quantity) {
        Product product = productList.search(productId);
        Client client = clientList.getClient(clientId);

        if (product == null || client == null) {
            System.out.println("Invalid client or product ID.");
            return;
        }

        int available = product.getStock();

        // Prepare a map for shipped items (for invoice generation)
        Map<Product, Integer> shippedItems = new LinkedHashMap<>();

        if (available >= quantity) {
            // Full fulfillment
            product.setStock(available - quantity);
            shippedItems.put(product, quantity);
            System.out.println("Order fulfilled for " + client.getName());
        } 
        else if (available > 0) {
            // Partial fulfillment → ship available items and waitlist the rest
            product.setStock(0);
            shippedItems.put(product, available);

            int remaining = quantity - available;
            product.addWaitlistItem(client.getId(), remaining);

            System.out.println(
                "Partially fulfilled: " + available + " shipped, " +
                remaining + " added to waitlist for " + client.getName()
            );
        } 
        else {
            // None available → entire order goes to waitlist
            product.addWaitlistItem(client, quantity);
            System.out.println(
                "All " + quantity + " units of " + product.getName() +
                " waitlisted for " + client.getName()
            );
        }

        // Generate an invoice only if something was shipped
        if (!shippedItems.isEmpty()) {
            generateInvoice(clientId, shippedItems);
        }
    }

    /** Fulfill waitlist for a specific product */
    public void processWaitlist(Product product) {
        double availableQty = product.getStock();
        List<WaitlistItem> waitlist = product.getWaitlist();

        if (availableQty <= 0 || waitlist.isEmpty()) {
            return; // Nothing to process
        }

        Iterator<WaitlistItem> iterator = waitlist.iterator();

        while (iterator.hasNext() && availableQty > 0) {
            WaitlistItem item = iterator.next();
            double need = item.getQuantity();

            if (availableQty >= need) {
                // Fully fulfill this waitlist entry
                Map<Product, Integer> shippedItems = new LinkedHashMap<>();
                shippedItems.put(product, need);
                generateInvoice(item.getClientId(), shippedItems);

                availableQty -= need;
                iterator.remove();
            } else {
                // Partially fulfill
                Map<Product, Integer> shippedItems = new LinkedHashMap<>();
                shippedItems.put(product, availableQty);
                generateInvoice(item.getClientId(), shippedItems);

                item.setQuantity(need - availableQty);
                availableQty = 0;
            }
        }

        // Update stock after fulfillment
        product.setStock(availableQty);
    }
    
    /** Receive a shipment and process the corresponding product's waitlist */
    public void receiveShipment(String productId, int qty) {
        Product product = productList.searchID(productId);
        if (product == null) {
            System.out.println("Sorry! Product not found for ID: " + productId);
            return;
        }

        double oldStock = product.getStock();
        product.setStock(oldStock + qty);  //  Update stock first

        System.out.println("Shipment received for " + product.getName());
        System.out.println("Old stock: " + oldStock);
        System.out.println("Added quantity: " + qty);

        // Now process waitlist for this product and update stock again
        processWaitlist(product);

        System.out.println("New stock after fulfilling waitlist: " + product.getStock());
    }

    /** Generate an invoice for a client */
    public Invoice generateInvoice(String clientId, Map<Product, Integer> productMap) {
        Client client = clientList.getClient(clientId);
        if (client == null) {
            System.out.println("Client not found.");
            return null;
        }

        Invoice invoice = new Invoice(client);

        for (Map.Entry<Product, Integer> entry : productMap.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            invoice.addItem(product, quantity);
        }

        client.addInvoice(invoice);
        invoice.printInvoice();
        System.out.println("Invoice generated for " + client.getName());
        return invoice;
    }



    // ---------------- Display Methods ----------------
    public void showProducts() {
        productList.printProduct();
    }

    public void showClients() {
        clientList.Print_ClientList();
    }
}
