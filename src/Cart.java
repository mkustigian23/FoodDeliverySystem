<<<<<<< Updated upstream
import java.util.HashMap;

public class Cart extends Restaurant {
    private String add;
    private String remove;

    public Cart(String name, String address, HashMap<String, Double> menu) {
        super(name, address, menu);
    }
    public String getAdd() {
        return add;
    }

    public String getRemove() {
        return remove;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public void setRemove(String remove) {
        this.remove = remove;
    }
    
>>>>>>> Stashed changes
    }
}
