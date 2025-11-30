/**
 * Documentation: LoginDAO
 *
 * This class manages all database operations with user logins. It handles creations of the logins table, inserting
 * new users, and demo users
 *
 * Module Purpose:
 * - Create the logins table with id, username, password, and account type
 * - Insert new login records
 * - Insert demo users for Admin, Customer, Driver, and Restaurant roles
 *
 * Key Methods:
 * - logout(): Logs out the current user by clearing the session information
 * - createTable(): Creates the logins table
 * - insert(String username, String password, int account_type): Inserts a new user into the logins table
 * - validateLogin(String username, String password): Validates a user's login credentials against the logins table
 *   in the database
 * - insertDefaultUsers(): Inserts default demo users into the logins table
 */

import java.sql.*;
import java.sql.*;

public class LoginDAO {

    // Track logged-in user
    private static String currentUser = null;
    private static int currentAccountType = -1;

    public static String getCurrentUser() { return currentUser; }
    public static int getCurrentAccountType() { return currentAccountType; }

    /**
     * Logs out the current user by clearing the session information
     * After calling this method, the app will treat the user as not logged in.
     */
    public static void logout() {
        currentUser = null;
        currentAccountType = -1;
    }

    // ========================================
    // CREATE TABLE (FIXED)
    // ========================================

    /**
     * Creates the logins table in the database
     *
     * @throws SQLException if a database access error occurs
     */
    public void createTable() throws SQLException {
        String sql = """
            CREATE TABLE IF NOT EXISTS logins (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                username TEXT NOT NULL UNIQUE,
                password TEXT NOT NULL,
                account_type INTEGER NOT NULL
            );
        """;

        try (Connection conn = DatabaseManager.connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        }
    }

    // ========================================
    // INSERT NEW USER (FIXED)
    // ========================================

    /**
     * Inserts a new user into the logins table
     *
     * @param username Username of the user
     * @param password password of the user
     * @param account_type Type of account (0 = Admin, 1 = customer, 2 = driver, 3 = restaurant)
     * @throws SQLException if a database access error occurs
     */
    public void insert(String username, String password, int account_type) throws SQLException {
        String sql = "INSERT INTO logins(username, password, account_type) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setInt(3, account_type);
            pstmt.executeUpdate();
        }
    }

    /**
     * Validates a user's login credentials against the logins table in the database
     * @param username username the user enters
     * @param password password the user enters
     * @return the account type of the user if the login is successful
     */
    public int validateLogin(String username, String password) {
        String sql = "SELECT account_type FROM logins WHERE username = ? AND password = ?";

        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                currentUser = username;
                currentAccountType = rs.getInt("account_type");
                return currentAccountType;
            }

        } catch (SQLException e) {
            System.out.println("Login error: " + e.getMessage());
        }
        return -1;
    }

    // ========================================
    // INSERT DEFAULT USERS (FIXED)
    // ========================================

    /**
     * Inserts default demo users into the logins table
     *  - Admin: username=FoodAdmin, password=COMP390
     *  - Customer: username=customerDemo, password= ilikefood
     *  - Driver: username=driverDemo, password=driving
     *  - Restaurant: username=restDemo, password=restaurant
     */
    public void insertDefaultUsers() {
        String sql = "INSERT OR IGNORE INTO logins(username, password, account_type) VALUES(?, ?, ?)";

        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Admin TYPE = 0
            pstmt.setString(1, "FoodAdmin");
            pstmt.setString(2, "COMP390");
            pstmt.setInt(3, 0);
            pstmt.executeUpdate();

            // Customer TYPE = 1
            pstmt.setString(1, "customerDemo");
            pstmt.setString(2, "ilikefood");
            pstmt.setInt(3, 1);
            pstmt.executeUpdate();

            // Driver TYPE = 2
            pstmt.setString(1, "driverDemo");
            pstmt.setString(2, "driving");
            pstmt.setInt(3, 2);
            pstmt.executeUpdate();

            // Restaurant TYPE = 3
            pstmt.setString(1, "restDemo");
            pstmt.setString(2, "restaurant");
            pstmt.setInt(3, 3);  // ← FIXED: must be 3
            pstmt.executeUpdate();


        } catch (SQLException e) {
            System.out.println("Error inserting default users: " + e.getMessage());
        }
    }
}
