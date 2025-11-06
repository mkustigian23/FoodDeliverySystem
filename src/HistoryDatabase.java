
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HistoryDatabase {
    private static final String URL = "jdbc:sqlite:history.db";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}
