/**
 * Documentation: CustomerDAO
 *
 * Handles all database operation for the customers table. It allows creating the table, inserting new customers,
 * and retrieving customer data
 *
 * Module Purpose:
 * - Manages the customers table
 * - Inserts new customer records
 * - Retrieves a list of all customers
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    /**
     *
     * Creates the customers table
     *
     * @throws SQLException if a database access error occurs
     */
    public void createTable() throws SQLException {
        String sql = """
            CREATE TABLE IF NOT EXISTS customers (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL,
                email TEXT UNIQUE NOT NULL
            );
        """;
        try (Connection conn = DatabaseManager.connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        }
    }

    /**
     * Insert a new customer into the customers table
     *
     * @param name the customers name
     * @param email the customers email
     * @return the ID of the inserted customer or a -1 if insertion fails
     * @throws SQLException if a database access error occurs
     */

    public int insert(String name, String email) throws SQLException {
        String sql = "INSERT INTO customers(name, email) VALUES(?, ?)";
        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
        }
        return -1;
    }

    /**
     * Retrieves all customers from the customers table
     *
     * @return a list of customer strings as "id: name"
     * @throws SQLException if a database access error occurs
     */

    public List<String> getAll() throws SQLException {
        List<String> list = new ArrayList<>();
        String sql = "SELECT * FROM customers";
        try (Connection conn = DatabaseManager.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(rs.getInt("id") + ": " + rs.getString("name"));
            }
        }
        return list;
    }
}
