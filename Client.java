public class Client {
    private String id;
    private String name;
    private Wishlist wishlist;
    private float balance;

    public Client(String id, String name, float startingBalance) {
        this.id = id;
        this.name = name;
        this.wishlist = new Wishlist();
        this.balance = startingBalance;
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
