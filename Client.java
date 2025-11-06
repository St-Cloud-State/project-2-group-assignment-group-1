import java.util.ArrayList;
public class Client {
    private final String id;
    private final String name;
    private Wishlist wishlist;
    private float balance;
    private final ArrayList<Transaction> transactions;

    public Client(String id, String name, float startingBalance) {
        this.id = id;
        this.name = name;
        this.wishlist = new Wishlist();
        this.balance = startingBalance;
        this.transactions = new ArrayList<>();
    }

    // Accessors
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getBalance(){
        return balance;
    }

    public float addToBalance(float addend){
        this.balance = balance + addend;
        return balance;
    }

    public void recordTransaction(Transaction newTransaction){
        this.transactions.add(newTransaction);
    }

    public void printTransactions(){
        if (transactions.isEmpty()){
            System.out.println("No current transactions");
        
        }

        System.out.println("Transactions for " + name + ":");
        for(Transaction t : transactions){
            System.out.println(t);
        }
    }


    // Wishlist operations
    public void addToWishlist(WishlistItem wishlistitem) {
        if (wishlist == null){
            wishlist = new Wishlist();
        }
        wishlist.insertItem(wishlistitem);
    }

    public Wishlist getWishlist() {
        return wishlist;
    }

    @Override
    public String toString() {
        return "Client id = " + id + " name = " + name + " balance = " + balance;
    }
}
