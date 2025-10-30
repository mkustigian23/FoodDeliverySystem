import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class RestaurantDAO {
    public void createTable() throws SQLException {
        String sql = """
            CREATE TABLE IF NOT EXISTS restaurants (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL,
                location TEXT NOT NULL
            );
        """;
        try (Connection conn = RestaurantDatabase.connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        }
    }

    public void insert(String name, String location) throws SQLException {
        String sql = "INSERT INTO restaurants(name, location) VALUES(?, ?)";
        try (Connection conn = RestaurantDatabase.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, location);
            pstmt.executeUpdate();
        }
    }

    public List<Restaurant> getAll() throws SQLException {
        List<Restaurant> restaurants = new ArrayList<>();
        String sql = "SELECT * FROM restaurants";
        try (Connection conn = RestaurantDatabase.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                restaurants.add(new Restaurant(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("location")
                ));
            }
        }
        return restaurants;
    }
}

