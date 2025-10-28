
/**
 * A test file for final implementation of project 1
 *
 * @author Aidan Adams
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class stage2final {

    public static void main(String[] args) {
        ClientList c_list = new ClientList();
        ProductList p_list = new ProductList();
        Waitlist waitlist = new Waitlist();
        ArrayList<Wishlist> allWishlists = new ArrayList<>();
        Scanner menuInput = new Scanner(System.in);
        boolean endMenu = false;
        while (!endMenu) {
            System.out.println("Here are your options:");
            System.out.println("1: Add client to system");
            System.out.println("2: Print all clients");
            System.out.println("3: Add Product to system");
            System.err.println("4: Print all products");
            System.out.println("5: Add item to a wishlist");
            System.out.println("6: process order");
            System.out.println("7: print waitlist");
            System.out.println("8: add payment");
            System.out.println("9: add stock ");
            System.out.println("10: End program");
            System.out.println("Enter a selection:");

            int menuSelection = menuInput.nextInt();
            menuInput.nextLine();
            switch (menuSelection) {
                case 1:
                    System.out.println("What will the name be?");
                    String clientString = menuInput.nextLine();
                    System.out.println("What will the id be?");
                    String clientId = menuInput.nextLine();
                    
                    Client clientName = new Client(clientId, clientString, 50);
                    c_list.addClient(clientName);
                    System.out.println("Client added");
                    break;
                case 2:
                    c_list.Print_ClientList();
                    break;
                case 3:
                    System.out.println("What will the name be?");
                    String productName = menuInput.nextLine();
                    System.out.println("What will the price be?");
                    double productPrice = menuInput.nextDouble();
                    System.out.println("What will the stock be?");
                    double productStock = menuInput.nextDouble();
                    menuInput.nextLine();
                    
                    p_list.addProduct(productName, productPrice, productStock);
                    System.out.println("Product added");
                    break;
                case 4:
                    p_list.printProduct();
                    break;
                case 5:
                    System.out.println("Product to be added to wishlist?");
                    String nextProduct = menuInput.nextLine();
                    System.out.println("Who's wishlist are we adding this to?(remember it is id)");
                    String nextClient = menuInput.nextLine();
                    System.out.println("How many should we add to the list?");
                    int Amount_Added = menuInput.nextInt();
                    Product currentProduct = p_list.search(nextProduct);
                    Client currentClient = c_list.getClient(nextClient);
                    String currentName = currentProduct.getName();
                    String currentId = currentProduct.getID();
                    WishlistItem newItem = new WishlistItem(currentId, currentName, Amount_Added);
                    currentClient.addToWishlist(newItem);

                    System.out.println("Product added to wish list");
                    break;
                case 6:
                    System.out.println("Who's wishlist are we processing?(use id)");
                    String nextWish = menuInput.nextLine();
                    Client clientToProcess = c_list.getClient(nextWish);
                    List<WishlistItem> wishlist = clientToProcess.getWishlist();
                    if (wishlist.isEmpty()) {
                        System.out.println("break1");
                        break;

                    }
                    for (WishlistItem item : wishlist) {
                        Product product = p_list.search(item.getItemName());
                        if (product == null) {
                            continue;
                        }

                        int qty = item.getQuantity();
                        double totalCost = qty * product.getPrice();

                        if (product.getStock() >= qty) {
                            product.setStock(product.getStock() - qty);
                            clientToProcess.addToBalance((float) (-totalCost));
                        } else {
                            int available = (int) product.getStock();
                            int waitQty = qty - available;

                            if (available > 0) {
                                product.setStock(0);
                                clientToProcess.addToBalance((float) (-(available * product.getPrice())));
                            }
                            Waitlist waitlist_temp = product.getWaitlist();
                            waitlist_temp.addItem(clientToProcess.getId(), product.getID(), waitQty);
                        }
                    }

                    wishlist.clear();
                    System.out.println("Wishlist processed");
                    break;
                case 7:
                    System.out.println("Which product are we checking?");
                    String list_needed = menuInput.nextLine();
                    Product product = p_list.search(list_needed);
                    Waitlist temp = product.getWaitlist();
                    temp.printWaitlist();

                    break;
                case 8:
                    System.out.println("Who's account are we adding money to?");
                    String toAdd = menuInput.nextLine();
                    System.out.println("How much?");
                    float increase = menuInput.nextFloat();
                    Client clientToProcess2 = c_list.getClient(toAdd);
                    clientToProcess2.addToBalance(increase);
                    System.out.println(clientToProcess2.getBalance() + " is the current balance");
                    break;
                case 9:
                    ReceivingShipment shipment = new ReceivingShipment(p_list);

                    System.out.println("Enter the product to update:");
                    String itemNameToUpdate = menuInput.nextLine();

                    System.out.println("Enter the new stock:");
                    int newStock = menuInput.nextInt();

                    shipment.receiveShipment(itemNameToUpdate, newStock);
                    

                    break;

                case 10:
                    endMenu = true;
                    break;
                default:
                    System.out.println("Not a valid input");

            }

        }
    }
}
