/**
 *author: Jiali Zhao 
 */
//package Pro1_Warehouse;

public class TestWaitlistItem {

    public static void main(String[] args) {
        System.out.println("=== Testing WaitlistItem Class ===");

        // Step 1: Create a WaitlistItem using clientId & productId
        WaitlistItem item1 = new WaitlistItem("C1", "P1", 5);
        System.out.println("Created item1: " + item1);

        // Step 2: Verify getter methods
        System.out.println("\n=== Getter Tests ===");
        System.out.println("Client ID: " + item1.getClientId());
        System.out.println("Product ID: " + item1.getProductId());
        System.out.println("Quantity: " + item1.getQuantity());
        System.out.println("Fulfilled? " + item1.isFulfilled());
        System.out.println("Date Added: " + item1.getDateAdded());

        // Step 3: Test updating quantity
        System.out.println("\n=== Quantity Update Test ===");
        System.out.println("Original quantity: " + item1.getQuantity());
        item1.setQuantity(8);
        System.out.println("Updated quantity: " + item1.getQuantity());

        // Step 4: Mark as fulfilled
        System.out.println("\n=== Fulfillment Test ===");
        System.out.println("Fulfilled before mark? " + item1.isFulfilled());
        item1.markFulfilled();
        System.out.println("Fulfilled after mark? " + item1.isFulfilled());

        // Step 5: Print toString() again (should show updated quantity + fulfilled = true)
        System.out.println("\n=== Final Object State ===");
        System.out.println(item1);

        // Step 6: (Optional) Create a second item to ensure date and IDs are unique
        WaitlistItem item2 = new WaitlistItem("C2", "P2", 3);
        System.out.println("\nCreated item2: " + item2);
    }
}

