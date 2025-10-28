//package pro1;

import java.util.*;

public class TestProductList {
    public static void main(String[] args) {
        // ============================
        // Create ProductList
        // ============================
        ProductList list = new ProductList();

        // ============================
        // Test addProduct: add 3 normal products
        // ============================
        System.out.println("=== Testing addProduct ===");
        Product appleProduct = list.addProduct("Apple", 1.5, 100);
        System.out.println("Added product: " + appleProduct);

        Product bananaProduct = list.addProduct("Banana", 2.0, 50);
        System.out.println("Added product: " + bananaProduct);

        Product orangeProduct = list.addProduct("Orange", 3.0, 80);
        System.out.println("Added product: " + orangeProduct);

        // ============================
        // Test batch addProducts
        // ============================
        System.out.println("\n=== Testing addProducts (batch add) ===");
        Product grape = new Product("Grape", 4.5, 30);
        Product pear = new Product("Pear", 2.5, 60);
        List<Product> addedBatch = list.addProducts(grape, pear);
        System.out.println("Batch added products:");
        for (Product p : addedBatch) {
            System.out.println(p);
        }

        // ============================
        // Test printProduct
        // ============================
        System.out.println("\n=== Testing printProduct ===");
        list.printProduct();

        // ============================
        // Test search with a fake ID (does not exist)
        // ============================
        System.out.println("\n=== Testing search with fake ID ===");
        Product temp = new Product("Test", 9.9, 10);
        String fakeID = temp.getID();
        System.out.println("Search fake ID " + fakeID + ": " + list.search(fakeID));

        // ============================
        // Test search with real IDs
        // ============================
        System.out.println("\n=== Testing search with real IDs ===");
        System.out.println("Search apple ID " + appleProduct.getID() + ": " + list.search(appleProduct.getID()));
        System.out.println("Search banana ID " + bananaProduct.getID() + ": " + list.search(bananaProduct.getID()));
        System.out.println("Search orange ID " + orangeProduct.getID() + ": " + list.search(orangeProduct.getID()));

        // ============================
        // Test duplicate product addition
        // ============================
        System.out.println("\n=== Testing duplicate product addition ===");
        Product duplicateApple = list.addProduct("Apple", 1.5, 100);
        System.out.println("Added duplicate product: " + duplicateApple);
        System.out.println("Current product list:");
        list.printProduct();

        // ============================
        // Test search in empty ProductList
        // ============================
        System.out.println("\n=== Testing search in empty ProductList ===");
        ProductList emptyList = new ProductList();
        System.out.println("Search any ID in empty list: " + emptyList.search("anyID"));
        System.out.println("Print empty list:");
        emptyList.printProduct();

        // ============================
        // Test ID uniqueness
        // ============================
        System.out.println("\n=== Testing ID uniqueness ===");
        List<Product> allProducts = Arrays.asList(appleProduct, bananaProduct, orangeProduct, duplicateApple);
        Set<String> ids = new HashSet<>();
        boolean duplicateFound = false;
        for (Product p : allProducts) {
            if (!ids.add(p.getID())) {
                System.out.println("Duplicate ID found: " + p.getID());
                duplicateFound = true;
            }
        }
        if (!duplicateFound) {
            System.out.println("All product IDs are unique.");
        }

        System.out.println("\n=== All tests completed ===");
    }
}
