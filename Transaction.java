package Pro1_Warehouse;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction implements Serializable {
    private static final long serialVersionUID = 1L;

    private Date date;          // When it happened
    private String description; // "Order processed", "Payment received", "Invoice generated", etc.
    private double amount;      // Positive for payments, negative for charges

    public Transaction(String description, double amount) {
        this.date = new Date(); // current timestamp
        this.description = description;
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return String.format("[%s] %-25s $%.2f", sdf.format(date), description, amount);
    }
}
