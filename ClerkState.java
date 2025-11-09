
/**
 * @author Sunny
 */
import java.util.Scanner;

public class ClerkState extends wareHouseState {
    private static ClerkState instance;
    private Warehouse warehouse = Warehouse.instance();
    private Scanner scanner = new Scanner(System.in);

    private ClerkState() {
    }

    public static ClerkState instance() {
        if (instance == null) {
            instance = new ClerkState();
        }
        return instance;
    }

    @Override
    public void run() {
        boolean running = true;
        while (running) {
            System.out.println("\n=== Clerk Menu ===");
            System.out.println("1. Add a Client");
            System.out.println("2. Show List of Products");
            System.out.println("3. Show List of Clients");
            System.out.println("4. Show Clients with Outstanding Balance");
            System.out.println("5. Record Payment from a Client");
            System.out.println("6. Become a Client");
            System.out.println("7. Logout");
            System.out.print("Enter choice: ");

            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    addClient();
                    break;
                case "2":
                    warehouse.showProducts();
                    break;
                case "3":
                    warehouse.showClients();
                    break;
                case "4":
                    showClientsWithBalance();
                    break;
                case "5":
                    recordPayment();
                    break;
                case "6":
                    becomeClient();
                    break;
                case "7":
                    logout();
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void addClient() {
        System.out.print("Enter new client name: ");
        String name = scanner.nextLine();
        System.out.print("Enter client ID: ");
        String id = scanner.nextLine();

        Client newClient = new Client(id, name, 0);
        boolean added = warehouse.insertClient(name, id);

        if (added)
            System.out.println("Client added successfully: " + newClient);
        else
            System.out.println("Client ID already exists.");
    }

    private void showClientsWithBalance() {
        System.out.println("\nClients with outstanding balance:");
        ClientList tempc_list = warehouse.getClientList();
        for (Client c : tempc_list.getAllClients()) {
            if (c.getBalance() < 0)
                System.out.println(c);
        }
    }

    private void recordPayment() {
        System.out.print("Enter client ID: ");
        String id = scanner.nextLine();
        Client client = warehouse.searchClient(id);
        if (client == null) {
            System.out.println("Client not found.");
            return;
        }

        System.out.print("Enter payment amount: ");
        float amount = Float.parseFloat(scanner.nextLine());
        client.addToBalance(amount);
        System.out.println("Updated balance: " + client.getBalance());
    }

    private void logout(){

       if(!(wareHouseContext.instance().isManager())){
        //Transitions back to login state
        wareHouseContext.instance().changeState(0);
       }
       //!!!This probably doesn't work yet, will need the full warehouseContext to be able to transistion back to the clerk state!!!
       //!!!Transitions to clerk state (once all the code it avalible)!!!
       else if (wareHouseContext.instance().isManager()) {
           wareHouseContext.instance().changeState(3);
       }
    }
    private void becomeClient() {
        System.out.print("Enter Client ID to act as: ");
        String clientId = scanner.nextLine();

        if (warehouse.searchClient(clientId) != null) {
            wareHouseContext.instance().setLogin("0");
            wareHouseContext.instance().setUser(clientId);
            System.out.println("Switching to client view...");
            wareHouseContext.instance().changeState(1); // ClientState
        } else {
            System.out.println("Client not found.");
        }
    }
}
