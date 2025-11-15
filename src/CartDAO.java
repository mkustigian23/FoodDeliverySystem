import java.util.ArrayList;
import java.util.List;

    public class CartDAO {
        private static final List<Menu> cartItems = new ArrayList<>();

        public static void addItem(Menu item) {
            cartItems.add(item);
        }

        public static void removeItem(Menu item) {
            cartItems.remove(item);
        }

        public static List<Menu> getItems() {
            return cartItems;
        }

        public static double getTotal() {
            return cartItems.stream().mapToDouble(Menu::getPrice).sum();
        }
    }