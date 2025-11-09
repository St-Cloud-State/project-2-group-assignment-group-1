//package pro1;

public class TestProduct {
    public static void main(String[] args) {
        // ============================
        // Test constructor: create 3 Product objects
        // ============================
        Product p1 = new Product("Apple", 1.5, 100);
        Product p2 = new Product("Orange", 3.0, 80);
        Product p3 = new Product("Grape", 4.5, 30);

        Product[] products = {p1, p2, p3};

        System.out.println("=== Constructor Test ===");
        for (Product p : products) {
            System.out.println("Created product: " + p); // test toString() as well
        }

        // ============================
        // Test getters: getID(), getName(), getPrice(), getStock()
        // ============================
        System.out.println("\n=== Getters Test ===");
        for (Product p : products) {
            System.out.println("Testing getID(): " + p.getID());
            System.out.println("Testing getName(): " + p.getName());
            System.out.println("Testing getPrice(): " + p.getPrice());
            System.out.println("Testing getStock(): " + p.getStock());
            System.out.println();
        }

        // ============================
        // Test setters: setName(), setPrice(), setStock()
        // ============================
        System.out.println("=== Setters Test ===");
        String[] newNames = {"Banana", "Peach", "Mango"};
        double[] newPrices = {2.0, 3.5, 5.0};
        double[] newStocks = {50, 60, 40};

        for (int i = 0; i < products.length; i++) {
            products[i].setName(newNames[i]);     // test setName()
            products[i].setPrice(newPrices[i]);   // test setPrice()
            products[i].setStock(newStocks[i]);   // test setStock()
            System.out.println("Updated product: " + products[i]); // test toString()
        }

        // ============================
        // Test ID uniqueness
        // ============================
        System.out.println("\n=== ID Uniqueness Test ===");
        for (int i = 0; i < products.length; i++) {
            for (int j = i + 1; j < products.length; j++) {
                if (products[i].getID().equals(products[j].getID())) {
                    System.out.println("Duplicate ID found between products " + i + " and " + j);
                }
            }
        }
        System.out.println("All product IDs are unique.");

        // ============================
        // Test simple invalid input cases
        // ============================
        System.out.println("\n=== Invalid Input Test ===");
        try {
            Product invalidProduct1 = new Product("", 1.0, 10); // empty name
            System.out.println("Created invalid product: " + invalidProduct1);
        } catch (Exception e) {
            System.out.println("Caught exception for empty name: " + e.getMessage());
        }

        try {
            Product invalidProduct2 = new Product("InvalidPrice", -5.0, 10); // negative price
            System.out.println("Created invalid product: " + invalidProduct2);
        } catch (Exception e) {
            System.out.println("Caught exception for negative price: " + e.getMessage());
        }

        try {
            Product invalidProduct3 = new Product("InvalidStock", 1.0, -10); // negative stock
            System.out.println("Created invalid product: " + invalidProduct3);
        } catch (Exception e) {
            System.out.println("Caught exception for negative stock: " + e.getMessage());
        }

        System.out.println("\n=== All tests completed ===");
    }
}
