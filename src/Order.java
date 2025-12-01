import java.util.List;

public class Order {
    private int orderId;
    private List<Menu> items;
    private double total;
    private String customerName;
    private String address;
    private String status;

    public Order(int orderId, List<Menu> items, double total,
                 String customerName, String address, String status) {
        this.orderId = orderId;
        this.items = items;
        this.total = total;
        this.customerName = customerName;
        this.address = address;
        this.status = status;
    }

    public int getOrderId() { return orderId; }
    public List<Menu> getItems() { return items; }
    public double getTotal() { return total; }
    public String getCustomerName() { return customerName; }
    public String getAddress() { return address; }
    public String getStatus() { return status; }
}