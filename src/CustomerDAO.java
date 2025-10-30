
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    public void createTable() throws SQLException {
        String sql = """
            CREATE TABLE IF NOT EXISTS customers (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL,
                email TEXT NOT NULL UNIQUE
            );
        """;
        try (Connection conn = CustomerDatabase.connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        }
    }
    public boolean emailExists(String email) throws SQLException {
        String sql = "SELECT COUNT(*) FROM customers WHERE email = ?";
        try (Connection conn = CustomerDatabase.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            return rs.getInt(1) > 0;
        }
    }

    public void insert(String name, String email) throws SQLException {
        if (emailExists(email)) {
            throw new SQLException("Email already exists: " + email);
        }

        String sql = "INSERT INTO customers(name, email) VALUES(?, ?)";
        try (Connection conn = CustomerDatabase.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.executeUpdate();
        }
    }

    public List<Customer> getAll() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customers";
        try (Connection conn = CustomerDatabase.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                customers.add(new Customer(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email")
                ));
            }
        }
        return customers;
    }
}

