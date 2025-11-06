package Pro1_Warehouse;
import java.io.Serializable;
import java.util.*;

public class Invoice implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int nextId = 1;

    private String invoiceId;
    private Date date;
    private Client client;

    // key: product name, value: [unit price, quantity]
    private Map<String, double[]> productDetails = new LinkedHashMap<>();
    private double totalCost;

    public Invoice(Client client) {
        this.invoiceId = generateId();
        this.client = client;
        this.date = new Date();
    }

    private static String generateId() {
        return String.format("I%03d", nextId++);
    }

    public void addItem(Product product, int quantity) {
        double price = product.getPrice();
        productDetails.put(product.getName(), new double[]{price, quantity});
        totalCost += price * quantity;
    }

    public double getTotalCost() { return totalCost; }

    public void printInvoice() {
        System.out.println("===== INVOICE " + invoiceId + " =====");
        System.out.println("Client: " + client.getName());
        System.out.println("Date: " + date);
        for (Map.Entry<String, double[]> entry : productDetails.entrySet()) {
            String name = entry.getKey();
            double[] detail = entry.getValue();
            double price = detail[0];
            int qty = (int) detail[1];
            System.out.printf("%-15s x%2d @ $%.2f = $%.2f%n",
                    name, qty, price, price * qty);
        }
        System.out.printf("Total: $%.2f%n", totalCost);
        System.out.println("==========================");
    }
}
