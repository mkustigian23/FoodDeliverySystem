
import java.sql.*;

public class DeliveryDAO {
    public void createTable() throws SQLException {
        String sql = """
            CREATE TABLE IF NOT EXISTS deliveries (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                customer_id INTEGER NOT NULL,
                driver_id INTEGER NOT NULL,
                restaurant_id INTEGER NOT NULL,
                delivery_time TEXT,
                FOREIGN KEY (customer_id) REFERENCES customers(id) ON DELETE CASCADE,
                FOREIGN KEY (driver_id) REFERENCES drivers(id) ON DELETE CASCADE,
                FOREIGN KEY (restaurant_id) REFERENCES restaurants(id) ON DELETE CASCADE
            );
        """;
        try (Connection conn = DatabaseManager.connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        }
    }

    public void insert(int customerId, int driverId, int restaurantId, String deliveryTime) throws SQLException {
        String sql = "INSERT INTO deliveries(customer_id, driver_id, restaurant_id, delivery_time) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, customerId);
            pstmt.setInt(2, driverId);
            pstmt.setInt(3, restaurantId);
            pstmt.setString(4, deliveryTime);
            pstmt.executeUpdate();
        }
    }

    public void printAllDeliveries() throws SQLException {
        String sql = """
            SELECT deliveries.id, customers.name AS customer, drivers.name AS driver,
                   restaurants.name AS restaurant, delivery_time
            FROM deliveries
            JOIN customers ON deliveries.customer_id = customers.id
            JOIN drivers ON deliveries.driver_id = drivers.id
            JOIN restaurants ON deliveries.restaurant_id = restaurants.id;
        """;
        try (Connection conn = DatabaseManager.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.printf("Delivery #%d | Customer: %s | Driver: %s | Restaurant: %s | Time: %s%n",
                        rs.getInt("id"),
                        rs.getString("customer"),
                        rs.getString("driver"),
                        rs.getString("restaurant"),
                        rs.getString("delivery_time"));
            }
        }
    }
}

