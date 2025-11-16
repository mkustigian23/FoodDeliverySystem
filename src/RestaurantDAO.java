/**
 * Documentation: RestaurantDAO
 *
 * This class is a DAO for the restaurants table in the database. It provides methods to create the restaurants table,
 * insert new restaurants, and retrieve all restaurants.
 *
 * Module Purpose:
 * - Manage the database table for restaurants
 * - Provide data access for UI classes like CustomerFrame
 *
 * Key Methods:
 * createTable():
 * - Creates the restaurants table in the database
 *
 * insert(String name, String location, String imagePath):
 * - Method that inserts restaurants into the SQLite table restaurant
 *
 * getAll():
 * - Method that gets all restaurants and their data in the table "restaurants"
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RestaurantDAO {

    /**
     * Creates the restaurants table in the database
     *
     * @throws SQLException if database access error occurs
     */
    public void createTable() throws SQLException {
        String sql = """
                    CREATE TABLE IF NOT EXISTS restaurants (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        name TEXT NOT NULL,
                        location TEXT NOT NULL,
                        image_Path TEXT
                    );
                """;
        try (Connection conn = DatabaseManager.connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        }
    }

    /**
     * Method that inserts restaurants into the SQLite table restaurant
     *
     * @param name name of the restaurant
     * @param location location of the restaurant
     * @return int the generated ID of the inserted restaurant or -1 if insertion fails
     * @throws SQLException if database access error occurs
     */
    public int insert(String name, String location, String imagePath) throws SQLException {
        String sql = "INSERT INTO restaurants(name, location, image_Path) VALUES(?, ?, ?)";
        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, name);
            pstmt.setString(2, location);
            pstmt.setString(3, imagePath);
            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
        }
        return -1;
    }

    /**
     * Method that gets all restaurants and their data in the table "restaurants"
     *
     * @return List<Restaurant> a list of restaurant objects
     * @throws SQLException if database access error occurs
     */
    public List<Restaurant> getAll() throws SQLException {
        List<Restaurant> list = new ArrayList<>();
        String sql = "SELECT * FROM restaurants";
        try (Connection conn = DatabaseManager.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String location = rs.getString("location");
                String imagePath = rs.getString("image_Path");

                list.add(new Restaurant(id, name, location, imagePath));
            }
        }
        return list;
    }

}