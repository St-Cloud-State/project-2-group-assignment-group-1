/**
 * @author Sunny
 */
import java.util.Scanner;

public class ManagerState extends wareHouseState {
    private static ManagerState instance;
    private Warehouse warehouse = Warehouse.instance();
    private Scanner scanner = new Scanner(System.in);

    private ManagerState() {
    }

    public static ManagerState instance() {
        if (instance == null) {
            instance = new ManagerState();
        }
        return instance;
    }

    @Override
    public void run() {
        boolean running = true;
        while (running) {
            System.out.println("\n=== Manager Menu ===");
            System.out.println("1. Add Product");
            System.out.println("2. Display Waitlist for Product");
            System.out.println("3. Receive Shipment");
            System.out.println("4. Show All Products");
            System.out.println("5. Become a Clerk");
            System.out.println("6. Logout");
            System.out.print("Enter choice: ");

            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    addProduct();
                    break;
                case "2":
                    displayWaitlist();
                    break;
                case "3":
                    receiveShipment();
                    break;
                case "4":
                    warehouse.getProductList().printProduct();
                    break;
                case "5":
                    becomeClerk();
                    break;
                case "6":
                    System.out.println("Logging out...");
                    wareHouseContext.instance().changeState(0); // back to login
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void addProduct() {
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter product price: ");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter product stock: ");
        double stock = Double.parseDouble(scanner.nextLine());

        // ProductList allProductList = Warehouse.instance().getProductList();

        // allProductList.addProduct(name, price, stock);
        warehouse.insertProduct(name, stock, price);

        System.out.println("Product added: " + name);
    }

    private void displayWaitlist() {
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        Product p = warehouse.getProductList().search(name);
        if (p == null) {
            System.out.println("Product not found.");
            return;
        }
        System.out.println("\nWaitlist for " + p.getName() + ":");
        p.getWaitlist().printWaitlist();
    }

    private void receiveShipment() {
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        // Product p = warehouse.getProductList().search(name);
        // if (p == null) {
        //     System.out.println("Product not found.");
        //     return;
        // }

        System.out.print("Enter quantity received: ");
        int qty = Integer.parseInt(scanner.nextLine());

    warehouse.receiveShipment(name, qty);
}
    private void becomeClerk() {
        System.out.println("Switching to clerk view...");
        wareHouseContext.instance().setLogin("0");
        wareHouseContext.instance().changeState(2); // ClerkState index (adjust per context matrix)
    }
}
