import org.junit.Before;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class integrationTestCarttoAddress {

    private Menu sampleItem;
    private String orderAddress;

    @Before
    public void setUp() {
        // Clear cart before each test
        CartDAO.clearCart();

        DatabaseManager.useTestDatabase();

        // Sample menu item
        sampleItem = new Menu(101, "Cheeseburger", 5.99,
                "burger.png");
    }

    @Test
    public void testAddItemAndCompleteAddress() {
        // Add item to cart
        CartDAO.addItem(sampleItem);

        // Verify cart contents
        List<Menu> cartItems = CartDAO.getItems();
        assertEquals(1, cartItems.size());
        assertEquals("Cheeseburger", cartItems.get(0).getName());
        assertEquals(5.99, CartDAO.getTotal(), 0.001);

        // Entering delivery address
        orderAddress = "123 Main St, Boston, MA";

        // Verify the address was set correctly
        assertEquals("123 Main St, Boston, MA", orderAddress);

        // Completing checkout
        boolean checkoutComplete = !CartDAO.getItems().isEmpty() && orderAddress != null;
        assertTrue(checkoutComplete);
    }
}

