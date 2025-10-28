

import java.util.Scanner;

public class ProductList_Product_Main {
    public static void main(String[] args) {
        ProductList list = new ProductList();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Product Management System ===");

        boolean running = true;
        while (running) {
            System.out.println("\nPlease choose an option:");
            System.out.println("1. Add a product");
            System.out.println("2. View all products");
            System.out.println("3. Search product by ID");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    // Add a single product
                    System.out.print("Enter product name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter product price: ");
                    double price = scanner.nextDouble();

                    System.out.print("Enter product stock: ");
                    double stock = scanner.nextDouble();

                    Product newProduct = list.addProduct(name, price, stock);
                    System.out.println("Product added: " + newProduct);
                    break;

                case 2:
                    // Print all products
                    System.out.println("\n=== Product List ===");
                    list.printProduct();
                    break;

                case 3:
                    // Search product by ID
                    System.out.print("Enter product ID to search: ");
                    String id = scanner.nextLine();
                    Product found = list.search(id);
                    if (found != null) {
                        System.out.println("Product found: " + found);
                    } else {
                        System.out.println("No product found with ID " + id);
                    }
                    break;

                case 4:
                    // Exit program
                    running = false;
                    System.out.println("Program exited. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}
