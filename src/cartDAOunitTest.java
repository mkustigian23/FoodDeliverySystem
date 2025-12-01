//cart DAO unitTest 1
//cart DAO unitTest 2


 import org.junit.jupiter.api.BeforeEach;
 import org.junit.jupiter.api.Test;
 import static org.junit.jupiter.api.Assertions.*;

 public class cartDAOunitTest {

    private Menu sampleItem1;
    private Menu sampleItem2;

    @BeforeEach
    public void setUp() {
     // Clear cart before each test to isolate tests
        CartDAO.clearCart();

        DatabaseManager.useTestDatabase();


        // Sample menu items
        sampleItem1 = new Menu(101, "Cheeseburger", 5.99, "src/images/cheeseburger.png");
        sampleItem2 = new Menu(102, "Fries", 2.99, "src/images/fries.png");
        }

     @Test
     public void testAddItem() {
        CartDAO.addItem(sampleItem1);
         assertTrue(CartDAO.getItems().contains(sampleItem1));
         assertEquals(1, CartDAO.getItems().size());
         assertEquals(5.99, CartDAO.getTotal(), 0.001);
         }

     @Test
     public void testRemoveItem() {
         CartDAO.addItem(sampleItem1);
         CartDAO.removeItem(sampleItem1);
         assertFalse(CartDAO.getItems().contains(sampleItem1));
         assertEquals(0, CartDAO.getItems().size());
         assertEquals(0.0, CartDAO.getTotal(), 0.001);
         }

     @Test
     public void testAddMultipleItems() {
         CartDAO.addItem(sampleItem1);
         CartDAO.addItem(sampleItem2);
         assertEquals(2, CartDAO.getItems().size());
         assertEquals(8.98, CartDAO.getTotal(), 0.001);
         }

     @Test
     public void testClearCart() {
         CartDAO.addItem(sampleItem1);
         CartDAO.addItem(sampleItem2);
         CartDAO.clearCart();
         assertEquals(0, CartDAO.getItems().size());
         assertEquals(0.0, CartDAO.getTotal(), 0.001);
         }

     }



