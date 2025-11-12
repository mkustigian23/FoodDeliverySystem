
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeliveryDAO {

    // creating a SQLite deliveries table; id, customer_id, driver_id, restaurant_id,

    /**
     *
     * @throws SQLException
     */
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

    /**
     *
     * @param customerId
     * @param driverId
     * @param restaurantId
     * @param deliveryTime
     * @throws SQLException
     */
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



    // Method for customer UI to be able to see all of their past orders for only the customer.

    /**
     *
     * @param customerId
     * @return
     * @throws SQLException
     */
    public List<String> getDeliveriesByCustomer(int customerId) throws SQLException {
        List<String> list = new ArrayList<>();
        String sql = """
        SELECT deliveries.id, drivers.name AS driver, restaurants.name AS restaurant, delivery_time
        FROM deliveries
        JOIN drivers ON deliveries.driver_id = drivers.id
        JOIN restaurants ON deliveries.restaurant_id = restaurants.id
        WHERE deliveries.customer_id = ?
        ORDER BY deliveries.delivery_time DESC;
    """;

        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, customerId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(String.format("Delivery #%d | Driver: %s | Restaurant: %s | Time: %s",
                        rs.getInt("id"),
                        rs.getString("driver"),
                        rs.getString("restaurant"),
                        rs.getString("delivery_time")));
            }
        }
        return list;
    }

    // Driver method to get all of the driver's past deliveries that they have made

    /**
     *
     * @param driverId
     * @return
     * @throws SQLException
     */
    public List<String> getDeliveriesByDriver(int driverId) throws SQLException {
        List<String> list = new ArrayList<>();
        String sql = """
        SELECT deliveries.id, customers.name AS customer, restaurants.name AS restaurant, delivery_time
        FROM deliveries
        JOIN customers ON deliveries.customer_id = customers.id
        JOIN restaurants ON deliveries.restaurant_id = restaurants.id
        WHERE deliveries.driver_id = ?
        ORDER BY deliveries.delivery_time DESC;
    """;

        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, driverId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(String.format("Delivery #%d | Customer: %s | Restaurant: %s | Time: %s",
                        rs.getInt("id"),
                        rs.getString("customer"),
                        rs.getString("restaurant"),
                        rs.getString("delivery_time")));
            }
        }
        return list;
    }

    // Restaurant method to get all deliveries that the restaurant has previously sent out.

    /**
     *
     * @param restaurantId
     * @return
     * @throws SQLException
     */
    public List<String> getDeliveriesByRestaurant(int restaurantId) throws SQLException {
        List<String> list = new ArrayList<>();
        String sql = """
        SELECT deliveries.id, customers.name AS customer, drivers.name AS driver, delivery_time
        FROM deliveries
        JOIN customers ON deliveries.customer_id = customers.id
        JOIN drivers ON deliveries.driver_id = drivers.id
        WHERE deliveries.restaurant_id = ?
        ORDER BY deliveries.delivery_time DESC;
    """;

        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, restaurantId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(String.format("Delivery #%d | Customer: %s | Driver: %s | Time: %s",
                        rs.getInt("id"),
                        rs.getString("customer"),
                        rs.getString("driver"),
                        rs.getString("delivery_time")));
            }
        }
        return list;
    }




    // ADMIN method showing all deliveries that have ever happened

    /**
     *
     * @throws SQLException
     */
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

