import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginDAOunitTest {

    private LoginDAO loginDAO;

    @BeforeEach
    public void setUp() throws Exception {
        loginDAO = new LoginDAO();
        loginDAO.createTable();
        loginDAO.insertDefaultUsers();
        LoginDAO.logout();
    }

    @Test
    public void testSuccessfulLogin() {
        boolean result = loginDAO.validateLogin("customerDemo", "ilikefood");

        // Expected output: user should be authenticated
        assertTrue(result, "User should be brought to the select a restaurant page");
        assertEquals("customerDemo", LoginDAO.getCurrentUser(), "Current user should be set correctly");
        assertEquals(1, LoginDAO.getCurrentAccountType(), "Account type should be customer (1)");
    }

    @Test
    public void testFailedLogin() {
        boolean result = loginDAO.validateLogin("customerDemo", "wrongPassword");

        // Expected output: login fails
        assertFalse(result, "User should see 'Invalid username or password'");
        assertNull(LoginDAO.getCurrentUser(), "No current user should be set");
        assertEquals(-1, LoginDAO.getCurrentAccountType(), "Account type should be reset");
    }
}
