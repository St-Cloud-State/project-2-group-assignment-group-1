public class ReceivingShipment {
    private ProductList productList;

    public ReceivingShipment(ProductList productList) {
        this.productList = productList;
    }

    // Receive shipment by product name (case-insensitive)
    public void receiveShipment(String productName, int qty) {
        Product product = findProductByName(productName);
        if (product == null) {
            System.out.println("Sorry! Product not found: " + productName);
            return;
        }

        double oldStock = product.getStock();
        //product.setStock(oldStock + qty); ----already included in processWaitlist

        System.out.println("Shipment received for " + productName);
        System.out.println("Old stock: " + oldStock);

        // calling the processWaitlist function for later implementation of waitlist
        product.processWaitlist(qty);

        System.out.println("New stock: " + product.getStock());
    }

    // Helper method to find product by name
    private Product findProductByName(String name) {
        var iterator = productList.getProducts();
        while (iterator.hasNext()) {
            Product p = iterator.next();
            if (p.getName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }

    public void printAllProducts() {
        productList.printProduct();
    }
}