
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CustomerDatabase {
    private static final String URL = "jdbc:sqlite:customer.db";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}

