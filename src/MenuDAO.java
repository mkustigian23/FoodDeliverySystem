/**
 * Documentation: MenuDAO
 *
 * This class is a DAO for the menu items in the database. It provides methods to create the menu table, insert new menu
 * item, and retrieve menu items
 *
 * Module Purpose:
 * - Manage the database table for menu items
 * - Provide data access for UI classes like MenuFrame
 *
 * Key Methods:
 * createTable():
 * - Creates the menu items table in the database
 * - Drops old tables to ensure correct table structure
 *
 * insert(int restaurantId, String name, double price, String menu_imagePath):
 * - Inserts a new menu item for the specific restaurant
 *
 * getMenuByRestaurant(int restaurantId)
 * - Retrieves the menu items for a specific restaurant
 */


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuDAO {

    /**
     * Creates the menu_items table in the database
     * Drops old tables to ensure correct table structure
     *
     * @throws SQLException if a database access error occurs
     */
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


    /**
     * Inserts a new menu item for the specific restaurant
     *
     * @param restaurantId the ID of the restaurant
     * @param name the name of the menu item
     * @param price the price of the menu item
     * @param menu_imagePath the path to the menu item image
     * @return int the generated ID of the inserted menu item or -1 if insertion fails
     * @throws SQLException if a database access error occurs
     */
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

    /**
     * Retrieves the menu items for a specific restaurant
     *
     * @param restaurantId the id of the restaurant
     * @return List<Menu> a list of menu objects for the restaurant
     * @throws SQLException if a database access error occurs
     */
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
