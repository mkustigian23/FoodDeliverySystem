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

        public static Integer checkLogin(String username, String password) {
            String sql = "SELECT account_type FROM logins WHERE username = ? AND password = ?";

            try (Connection conn = DatabaseManager.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, username);
                pstmt.setString(2, password);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    return rs.getInt("account_type");
                } else {
                    return null; // invalid login
                }

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }



