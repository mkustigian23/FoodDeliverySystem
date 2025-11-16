/**
 * Documentation: HistoryDAO
 *
 * Handles the database operations for the order history in the food delivery system.
 *
 * Module Purpose:
 * - Creates the history table for storing delivery records
 * - Checks for existing emails before inserting new customer history
 * - Inserts new customer order history entries
 * - Retrieves all customers from history database
 */

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoryDAO {
    // Creates a table inside of food_deliver.db called "history" where the delivery history is stored

    /**
     * Creates the history table in the database
     * @throws SQLException if a database access error occurs
     */
    public void createTable() throws SQLException {
        String sql = """
            CREATE TABLE IF NOT EXISTS history (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                restaurant TEXT NOT NULL,
                purchase INTEGER NOT NULL,
                name TEXT NOT NULL,
                email TEXT NOT NULL 
            );
        """;
        try (Connection conn = DatabaseManager.connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        }
    }

    /**
     * Method to check if an email exists in the SQLite table before adding it to the database
     *
     * @param email the email address to check
     * @return true if the email exists, false if not
     * @throws SQLException if a database access error occurs
     */
    public boolean emailExists(String email) throws SQLException {
        String sql = "SELECT COUNT(*) FROM customers WHERE email = ?";
        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            return rs.getInt(1) > 0;
        }
    }

    /**
     * Method to add an email/customer into the table and checks to see if the email already exists
     *
     * @param name Customers name
     * @param email Customers email
     * @throws SQLException if the email already exists or a database access error occurs
     */
    public void insert(String name, String email) throws SQLException {
        if (emailExists(email)) {
            throw new SQLException("Email already exists: " + email);
        }

        String sql = "INSERT INTO customers(name, email) VALUES(?, ?)";
        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.executeUpdate();
        }
    }

    /**
     * Method that gets all customers from the database
     *
     * @return list of customer objects representing all customers
     * @throws SQLException if a database access error occurs
     */
    public List<Customer> getAll() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customers";
        try (Connection conn = DatabaseManager.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                customers.add(new Customer(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email")
                ));
            }
        }
        return customers;
    }
}

