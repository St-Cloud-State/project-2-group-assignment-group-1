import java.util.ArrayList;

/**
 * Test file for Product, Productlist, Client, ClientList, Wishlist
 * 
 * 1. Make product and client work with wishlist
 * 
 */




public class Stage1Test{
   
   
    

    public static void main(String[] args) {

        //Creating a test product
        Product productA = new Product("Apple", 1.5, 27);
        
        //Adding the test product to a wish list. The number "6" was chosen arbitraily
        WishlistItem wishlistItemA = new WishlistItem(productA.getID(), productA.getName(), 6);

        //Making a test client with arbitrary id and name
        Client clientA = new Client("101", "John Doe",100);

        //An array of wishlistitems is needed when making a new wishlist. In this case, the array is empty
        //This is used purely for the inital creation of the wishlist
        ArrayList<WishlistItem> newClientWishList = new ArrayList<>();

        //To make a new wishlist, a client id and wishlistitem array is needed
        //To make the client id usable for wishlist, we must convert it from a string to an int
        //We should change either client or wishlist in the future so that they both use int or string, not both.
        //That can be done in a future implementation
        Wishlist wishlistA = new Wishlist(Integer.parseInt(clientA.getId()), newClientWishList);

        //Inserting the item into the wishlist 
        wishlistA.insertItem(wishlistItemA);

        //Printing out the item we inserted earlier
        System.out.println(wishlistA.getList().get(0).toString());

        // Testing the iterator and that wishlist works
        wishlistA.Print_Wishlist();
        // Testing the iterator and that clientlist works
        ClientList c_list = new ClientList();
        Client tester = new Client("id", "name",250);
        Client tester2 = new Client("id2", "name2",50);
        c_list.addClient(tester);
        c_list.addClient(tester2);
        c_list.Print_ClientList();


    }
}