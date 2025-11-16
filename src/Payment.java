/**
 * Documentation: Payment Class
 *
 * Payment class represents a simple payment processor for a purchase. It stores the transaction ammount,
 * processes the payment, and provides a receipt message.
 *
 * Module Purpose:
 * - Holds the total amount that the user is going to purchase
 * - Simulates a purchase action
 * - Provides a receipt to confirm the payment
 */
public class Payment {
    private int amount;


    /**
     * Creates a payment object with the total amount
     *
     * @param amount Passed in from the PaymentFrame, the total amount that the user needs to pay
     */
    public Payment(int amount) {
        this.amount = amount;
    }

    /**
     * Simulates processing a payment
     */
    public void purchase() {

    }

    /**
     * Provides a receipt message to show that the purchase was successful
     *
     * @return a receipt string showing the total amount that was paid and that the purchase was successful
     */
    public String sendReceipt() {
        return "Purchase was successful for $" + amount;
    }
}
