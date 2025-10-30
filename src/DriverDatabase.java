
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DriverDatabase {
    private static final String URL = "jdbc:sqlite:driver.db";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}

