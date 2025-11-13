import java.sql.*;
import java.sql.*;

public class LoginDAO {

    // ========================================
    // CREATE TABLE (FIXED)
    // ========================================
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

    // ========================================
    // INSERT DEFAULT USERS (FIXED)
    // ========================================
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
            pstmt.setInt(3, 3);  // ← FIXED: must be 3, not 2
            pstmt.executeUpdate();

            System.out.println("✅ Default users inserted (if not already present).");

        } catch (SQLException e) {
            System.out.println("Error inserting default users: " + e.getMessage());
        }
    }
}
