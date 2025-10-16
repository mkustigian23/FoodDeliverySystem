public class Payment {
    private int balance;

    public void setBalance(int b) {
        balance = b;
    }
    public int getBalance() {
        return balance;
    }
    public void purchase(int price) {
        balance = balance - price;
    }
    public String sendReceipt() {
        return ("Purchase was successful for $" + balance);
    }
}
