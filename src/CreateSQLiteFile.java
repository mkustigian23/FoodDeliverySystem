import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CreateSQLiteFile {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:food_delivery_test.db"; // File will be created here

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                System.out.println("Database file created successfully!");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
