//package pro1;

public class Product {
    // Fields
    private String name;
    private String id;
    private double price;
    private double stock;
    private Waitlist waitlist = new Waitlist();// Each product has its own waitlist

    // Constructor (id is auto-generated)
    public Product(String name, double price, double stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.id = java.util.UUID.randomUUID().toString(); // generate a unique ID
    }

    // Getters
    public String getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getStock() {
        return stock;
    }

    public Waitlist getWaitlist() { // for ReceivingShipment
        return waitlist; 
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }

    // Add a client to this product’s waitlist
    public void addWaitlistItem(String clientId, int quantity) {
        waitlist.addItem(clientId, this.id, quantity);
    }

    public void processWaitlist(int qty) {
        int remaining = getWaitlist().fulfillItems(qty);
        setStock(getStock() + remaining);
    }


    // Print product info
    @Override
    public String toString() {
        return "Product[ID=" + id + ", Name=" + name + ", Price=" + price + ", Stock=" + stock + "]";
    }
}



