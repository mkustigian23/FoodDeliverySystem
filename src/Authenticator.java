import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Authenticator {

    // Authenticator class to check a user's login information and validate their identity on the LoginFrame

    /**
     *
     * @param username
     * @param password
     * @return
     */
    public static boolean checkLogin(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

        try (Connection conn = DatabaseManager.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);  // In production, use hashed password

            ResultSet rs = stmt.executeQuery();
            return rs.next(); // returns true if a match is found

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}


