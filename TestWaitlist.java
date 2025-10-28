/**
 *author: Jiali Zhao 
 */
//package Pro1_Warehouse;

public class TestWaitlist {

    public static void main(String[] args) {

        // Create a Waitlist object
        Waitlist waitlist = new Waitlist();

        // Step 1: Add several waitlist items
        System.out.println("=== Adding waitlist items ===");
        waitlist.addItem("C1", "P1", 5);  // Client C1 waiting for 5 of Product P1
        waitlist.addItem("C2", "P1", 3);  // Client C2 waiting for 3 of Product P1
        waitlist.addItem("C1", "P1", 2);  // C1 adds 2 more — should merge with previous entry

        // Step 2: Print current waitlist
        System.out.println("\n=== Current Waitlist ===");
        waitlist.printWaitlist();

        // Step 3: Fulfill items (simulate receiving a shipment)
        System.out.println("\n=== Fulfilling with 6 units of Product P1 ===");
        int remaining = waitlist.fulfillItems(6);
        System.out.println("Remaining stock after fulfillment: " + remaining);

        // Step 4: Print waitlist again (to see what’s left)
        System.out.println("\n=== Waitlist After Fulfillment ===");
        waitlist.printWaitlist();

        // Step 5: Fulfill again (remaining 4 units)
        System.out.println("\n=== Fulfilling with 4 more units of Product P1 ===");
        remaining = waitlist.fulfillItems(4);
        System.out.println("Remaining stock after fulfillment: " + remaining);

        // Step 6: Final waitlist print
        System.out.println("\n=== Final Waitlist (should be empty) ===");
        waitlist.printWaitlist();
    }
}

