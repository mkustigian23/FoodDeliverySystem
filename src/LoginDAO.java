import java.sql.*;

public class LoginDAO {
    public void createTable() throws SQLException {
        String sql = """
            CREATE TABLE IF NOT EXISTS logins (
                username String NOT NULL UNIQUE,
                password String NOT NULL,
                account_type INTEGER NOT NULL,
            );
        """;
        try (Connection conn = DatabaseManager.connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        }
    }

    public void insert(String username, String password, int account_type) throws SQLException {
        String sql = "INSERT INTO logins(customer_id, driver_id, restaurant_id, delivery_time) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setInt(3, account_type);
            pstmt.executeUpdate();
        }
    }
}