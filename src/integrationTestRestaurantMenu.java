// Integration test cases Restaurant DAO+ Menu DAO
// Integration test cases Restaurant DAO + MenuDAO + CartDAO

import org.junit.Before;
import org.junit.Test;
import java.sql.SQLException;
import java.util.List;
import static org.junit.Assert.*;

public class integrationTestRestaurantMenu {

    private RestaurantDAO restaurantDAO;
    private MenuDAO menuDAO;

    @Before
    public void setUp() throws SQLException {
        // Use in-memory database for testing
        DatabaseManager.useTestDatabase();

        restaurantDAO = new RestaurantDAO();
        menuDAO = new MenuDAO();

        // Clear cart before each test
        CartDAO.clearCart();

        // Create tables in in-memory DB
        restaurantDAO.createTable();
        menuDAO.createTable();
    }

    // Test 1: Menu item gets added to the restaurant
    @Test
    public void testMenuItemAddedToRestaurant() throws SQLException {
        int restaurantId = restaurantDAO.insert("Test Restaurant", "Boston", "restaurant.png");
        int menuItemId = menuDAO.insert(restaurantId, "Cheeseburger", 5.99, "burger.png");

        List<Menu> items = menuDAO.getMenuByRestaurant(restaurantId);
        assertFalse(items.isEmpty());
        assertEquals("Cheeseburger", items.get(0).getName());
    }

    // Test 2: User selects restaurant and adds menu item to cart
    @Test
    public void testUserSelectsRestaurantAddsMenuItemToCart() throws SQLException {
        int restaurantId = restaurantDAO.insert("Test Restaurant", "Boston", "restaurant.png");
        int menuItemId = menuDAO.insert(restaurantId, "Cheeseburger", 5.99, "burger.png");

        List<Menu> menuItems = menuDAO.getMenuByRestaurant(restaurantId);
        Menu selectedItem = menuItems.get(0);
        CartDAO.addItem(selectedItem);

        List<Menu> cartItems = CartDAO.getItems();
        assertEquals(1, cartItems.size());
        assertEquals("Cheeseburger", cartItems.get(0).getName());
        assertEquals(5.99, CartDAO.getTotal(), 0.001);
    }
}
