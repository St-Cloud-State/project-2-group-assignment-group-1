
/**
 * @author Bryan Erickson
 */
import java.util.Scanner;

public class clientState extends wareHouseState {

    private static clientState clientState;
    
    private static final int logout = 0;
    private static final int showClientDetails = 1;
    private static final int showProductList = 2;
    private static final int showClientTransactions = 3;
    private static final int addToWishlist = 4;
    private static final int displayClientWishList = 5;
    private static final int placeOrder = 6;

    private clientState() {
        Warehouse warehouse = Warehouse.instance();
    }

    //Instance for the client state
    public static clientState instance() {
        if (clientState == null) {
            return clientState = new clientState();
        } else {
            return clientState;
        }
    }

    //Shows the client's (that logged in) details 
    public void showClientDetails() {
        System.out.println("Showing Client Details:");

        String userId = wareHouseContext.instance().getUser();
        Client currentClient = Warehouse.instance().searchClient(userId);

        System.out.println(currentClient.toString());
    }

    //Shows all products currently in the product list
    public void showProductList() {
        ProductList productList = Warehouse.instance().getProductList();
        productList.printProduct();
    }

    public void showClientTransactions() {
        System.out.println("I will show client transactions");
        String userId = wareHouseContext.instance().getUser();
        Client currentClient = Warehouse.instance().searchClient(userId);

        currentClient.printTransactions();
    }

    //Adds a product to the wishlist, product must be in productlist
    public void addToWishlist() {
        String userId = wareHouseContext.instance().getUser();
        Client currentClient = Warehouse.instance().searchClient(userId);

        Scanner scanner = wareHouseContext.instance().scanner;

        System.out.println("Enter the name of the item to add to your wish list:");
        String productToAdd = scanner.nextLine();
        System.out.println("What is the quantity to add?");
        int quantityToAdd = scanner.nextInt();
        scanner.nextLine();

        ProductList allProductList = Warehouse.instance().getProductList();
        Product nextProduct = allProductList.search(productToAdd);

        String currentName = nextProduct.getName();
        String currentId = nextProduct.getID();

        WishlistItem newItem = new WishlistItem(currentId, currentName, quantityToAdd);

        currentClient.addToWishlist(newItem);

    }

    //Displays the client's wishlist
    public void displayClientWishList() {
        String userId = wareHouseContext.instance().getUser();
        Client currentClient = Warehouse.instance().searchClient(userId);

        currentClient.getWishlist().Print_Wishlist();

    }

    //Processes the client's wishlist to place an order
    public void placeOrder() {
        System.out.println("Processing Wishlist");
        String userId = wareHouseContext.instance().getUser();
        Client currentClient = Warehouse.instance().searchClient(userId);
        ProductList allProductList = Warehouse.instance().getProductList();

        Wishlist wishlist = currentClient.getWishlist();

        for (WishlistItem item : wishlist.getItems()) {
            Product product = allProductList.search(item.getItemName());
            if (product == null) {
                continue;
            }

            int qty = item.getQuantity();
            double totalCost = qty * product.getPrice();

            if (product.getStock() >= qty) {
                product.setStock(product.getStock() - qty);
                currentClient.addToBalance((float) (-totalCost));

                

                Transaction newTransaction = new Transaction("Order placed for " + product.getName(), -totalCost);
                currentClient.recordTransaction(newTransaction);
            } else {
                int available = (int) product.getStock();
                int waitQty = qty - available;

                if (available > 0) {
                    product.setStock(0);
                    currentClient.addToBalance((float) (-(available * product.getPrice())));
                    Transaction partialTransaction = new Transaction("Partial Order for " + product.getName(), -(available * product.getPrice()));
                    currentClient.recordTransaction(partialTransaction);
                }
                Waitlist waitlist_temp = product.getWaitlist();
                waitlist_temp.addItem(currentClient.getId(), product.getID(), waitQty);

                Transaction waitTransaction = new Transaction("Waitlisted for " + product.getName(), 0.0);
                currentClient.recordTransaction(waitTransaction);

            }
        }
        currentClient.printTransactions();
        System.out.println("Wishlist Processed");
    }

    //Logs the user out to either the login state or the clerk state
    public void logout() {

       if(!(wareHouseContext.instance()).isClerk()){
        //Transitions back to login state
        wareHouseContext.instance().changeState(0);
       }
       //!!!This probably doesn't work yet, will need the full warehouseContext to be able to transistion back to the clerk state!!!
       //!!!Transitions to clerk state (once all the code it avalible)!!!
       else if (wareHouseContext.instance().isClerk()) {
           wareHouseContext.instance().changeState(2);
       }

    }

    //Shows the options menu for the client
    public void viewMenu() {
        System.out.println("-----Menu Options-----");
        System.out.println(showClientDetails + " to show client details");
        System.out.println(showProductList + " to show product list");
        System.out.println(showClientTransactions + " to show a client's transactions");
        System.out.println(addToWishlist + " to add to client's wishlist");
        System.out.println(displayClientWishList + " to show client's wishlist");
        System.out.println(placeOrder + " to place an order (Process Wishlist)");
        System.out.println(logout + " to logout");
    }

    //Gets the input for the clientstate menu
    public int getCommand() {
        System.out.println("-----Enter Command:-----");
        Scanner scanner = wareHouseContext.instance().scanner;
        int command = -1;
        while (true) {
            if (scanner.hasNextInt()) {
                command = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("Invalid Input");
                scanner.nextLine();
            }
            return command;
        }

    }

    //Processes the client getCommand() and viewMenu()
    public void process() {

        int command;
        do {
            viewMenu();
            command = getCommand();
            switch (command) {
                case logout:
                    logout();
                    break;
                case showClientDetails:
                    showClientDetails();
                    break;
                case showProductList:
                    showProductList();
                    break;
                case showClientTransactions:
                    showClientTransactions();
                    break;
                case addToWishlist:
                    addToWishlist();
                    break;
                case displayClientWishList:
                    displayClientWishList();
                    break;
                case placeOrder:
                    placeOrder();
                    break;
                
                

            }
            
        } while (command != logout);

    }

    public void run() {
        System.out.println("You are in client state");
        process();
    }
}
