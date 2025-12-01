/**
 * Documentation: DatabaseManager
 *
 * DatabaseManager handles the connection to the SQlite database used.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    // food_delivery.db is the SQLite database I have chosen for the restaurants, drivers and customers to be entered into

    private static String URL = "jdbc:sqlite:food_delivery.db";

    public static void useTestDatabase() {
        URL = "jdbc:sqlite:food_delivery_test.db";
    }

    /**
     * Establishes a connection to the SQlite database
     *
     * @return a connection object representing the database connection
     * @throws SQLException if a database access error occurs
     */
    public static Connection connect() throws SQLException {
        Connection conn = DriverManager.getConnection(URL);
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("PRAGMA foreign_keys = ON;");
        }
        return conn;
    }
}

