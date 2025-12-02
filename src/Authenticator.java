/**
 * Documentation: Authenticator
 *
 * Module Purpose:
 * This class validates user credentials and it's used by the loginframe to determine if a user can log in
 * and what type of account they have
 *
 * Key methods:
 * checkLogin(String username, String password):
 * - Checks if a username/password exists in the logins table
 * - Returns the account type of the user
 * - Returns null if credentials are invalid
 *
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Authenticator {

    // Authenticator class to check a user's login information and validate their identity on the LoginFrame

    /**
     * Checks login credentials of user
     *
     * @param username the username the user enters
     * @param password the password the user enters
     * @return integer representing the account type
     *      - Returns null if credentials are invalid
     */

    public static Integer checkLogin(String username, String password) {
        String sql = "SELECT account_type FROM logins WHERE username = ? AND password = ?";

        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, PasswordHash.hash(password));

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("account_type");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}



