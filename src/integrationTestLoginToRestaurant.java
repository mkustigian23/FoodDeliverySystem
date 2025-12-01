// Integration test cases login + Restaurant Frame

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class integrationTestLoginToRestaurant {

    @Before
    public void setUp() {
        // Ensure default users exist
        LoginDAO loginDAO = new LoginDAO();
        loginDAO.insertDefaultUsers();
    }

        @Test
        public void testLoginAndRestaurantFrameLoads() throws Exception {
            // User credentials
            String username = "customerDemo";
            String password = "ilikefood";

            // Validate login using actual method
            boolean loginSuccess = LoginDAO.validateLogin(username, password);
            assertTrue("Login should succeed", loginSuccess);

            // Verify login state
            assertEquals("customerDemo", LoginDAO.getCurrentUser());
            assertEquals(1, LoginDAO.getCurrentAccountType());

            // Open RestaurantFrame
            CustomerFrame frame = new CustomerFrame();
            assertNotNull(frame);
            assertTrue(frame.isDisplayable());


    }
}

