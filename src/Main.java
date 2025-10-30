
public class Main {

    class Login {

        public static void main(String[] args) throws Exception
        {
             new LoginFrame();
        }
    }

    public static void main(String[] args) {
        try {
            // Initialize DAOs
            CustomerDAO customerDAO = new CustomerDAO();
            DriverDAO driverDAO = new DriverDAO();
            RestaurantDAO restaurantDAO = new RestaurantDAO();

            // Create tables
            customerDAO.createTable();
            driverDAO.createTable();
            restaurantDAO.createTable();

            // Insert sample data
            customerDAO.insert("Alice", "alice@example.com");
            customerDAO.insert("Bob", "bob@example.com");

            driverDAO.insert("John Doe", "D12345");
            driverDAO.insert("Jane Smith", "D54321");

            restaurantDAO.insert("Pasta Palace", "New York");
            restaurantDAO.insert("Burger Barn", "Chicago");

            // Display all data
            System.out.println("=== Customers ===");
            customerDAO.getAll().forEach(System.out::println);

            System.out.println("\n=== Drivers ===");
            driverDAO.getAll().forEach(System.out::println);

            System.out.println("\n=== Restaurants ===");
            restaurantDAO.getAll().forEach(System.out::println);

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
