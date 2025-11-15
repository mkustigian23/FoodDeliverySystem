import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuDAO {

    // Create the menu_items table
    public void createTable() throws SQLException {
        try (Connection conn = DatabaseManager.connect();
             Statement stmt = conn.createStatement()) {

            // DELETE OLD TABLES (fixes old incorrect structure)
            stmt.execute("DROP TABLE IF EXISTS menu;");
            stmt.execute("DROP TABLE IF EXISTS menu_items;");

            // CREATE NEW CORRECT TABLE
            String sql = """
                        CREATE TABLE IF NOT EXISTS menu_items (
                            id INTEGER PRIMARY KEY AUTOINCREMENT,
                            restaurant_id INTEGER NOT NULL,
                            name TEXT NOT NULL,
                            price REAL NOT NULL,
                            menu_image_path TEXT,
                            FOREIGN KEY(restaurant_id) REFERENCES restaurants(id)
                        );
                    """;

            stmt.execute(sql);
        }
    }


    // Insert a menu item
    public int insert(int restaurantId, String name, double price, String menu_imagePath) throws SQLException {
        String sql = "INSERT INTO menu_items(restaurant_id, name, price, menu_image_path) VALUES(?, ?, ?, ?)";

        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, restaurantId);
            pstmt.setString(2, name);
            pstmt.setDouble(3, price);
            pstmt.setString(4, menu_imagePath);
            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
        }
        return -1;
    }

    // Get all menu items for a restaurant
    public List<Menu> getMenuByRestaurant(int restaurantId) throws SQLException {
        List<Menu> items = new ArrayList<>();
        String sql = "SELECT * FROM menu_items WHERE restaurant_id = ?";

        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, restaurantId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    items.add(new Menu(
                            rs.getInt("restaurant_id"),
                            rs.getString("name"),
                            rs.getDouble("price"),
                            rs.getString("menu_image_path")
                    ));
                }
            }
        }

        return items;
    }

}
