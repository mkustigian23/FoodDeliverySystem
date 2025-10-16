import java.util.HashMap;

public class Cart extends Restaurant {
    private String add;
    private String remove;

    public Cart(String name, String address, HashMap<String, Double> menu) {
        super(name, address, menu);
    }
}
