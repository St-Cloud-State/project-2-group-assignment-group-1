//package pro1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProductList {
    // Field: list of products
    private List<Product> productsList;

    // Constructor
    public ProductList() {
        productsList = new ArrayList<>();
    }

    // Search product by ID, returns Product if found, null if not
    public Product search(String productName) {
        for (Product p : productsList) {
            if (p.getName().equals(productName)) {
                return p;
            }
        }
        return null;
    }

    // Add a single new product
    public Product addProduct(String name, double price, double stock) {
        Product newProduct = new Product(name, price, stock);
        productsList.add(newProduct);
        return newProduct;
    }

    // Add multiple products at once (varargs)
    public List<Product> addProducts(Product... products) {
        List<Product> added = new ArrayList<>();
        for (Product p : products) {
            productsList.add(p);
            added.add(p);
        }
        return added;
    }

    // Print all products using Iterator
    public void printProduct() {
        if (productsList.isEmpty()) {
            System.out.println("No products available.");
        } else {
            Iterator<Product> it = productsList.iterator();
            while (it.hasNext()) {
                System.out.println(it.next());
            }
        }
    }

    // Return an iterator for external use
    public Iterator<Product> getProducts() {
        return productsList.iterator();
    }
}
