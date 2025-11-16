public class Payment {
    private int amount;

    public Payment(int amount) {
        this.amount = amount;
    }

    public void purchase() {
        // no logic needed for now
    }

    public String sendReceipt() {
        return "Purchase was successful for $" + amount;
    }
}
