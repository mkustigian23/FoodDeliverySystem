
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    // food_delivery.db is the SQLite database I have chosen for the restaurants, drivers and customers to be entered into

    private static final String URL = "jdbc:sqlite:food_delivery.db";

    /**
     *
     * @return
     * @throws SQLException
     */
    public static Connection connect() throws SQLException {
        Connection conn = DriverManager.getConnection(URL);
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("PRAGMA foreign_keys = ON;");
        }
        return conn;
    }
}

