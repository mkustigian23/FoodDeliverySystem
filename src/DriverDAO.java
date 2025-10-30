
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DriverDAO {
    public void createTable() throws SQLException {
        String sql = """
            CREATE TABLE IF NOT EXISTS drivers (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL,
                license_number TEXT UNIQUE NOT NULL
            );
        """;
        try (Connection conn = DriverDatabase.connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        }
    }

    public void insert(String name, String license) throws SQLException {
        String sql = "INSERT INTO drivers(name, license_number) VALUES(?, ?)";
        try (Connection conn = DriverDatabase.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, license);
            pstmt.executeUpdate();
        }
    }

    public List<Driver> getAll() throws SQLException {
        List<Driver> drivers = new ArrayList<>();
        String sql = "SELECT * FROM drivers";
        try (Connection conn = DriverDatabase.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                drivers.add(new Driver(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("license_number")
                ));
            }
        }
        return drivers;
    }
}

