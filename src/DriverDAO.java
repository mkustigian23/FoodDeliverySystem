/**
 * Documentation: DriverDAO
 *
 * This class handles database operation for drivers in the food delivery system. It allows creating the drivers table,
 * inserting new drivers, and retrieving all drivers.
 *
 * Module Purpose:
 * - Create the SQLite drivers table
 * - Insert new drivers into the table
 * - Retrieve all driver entries
 *
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DriverDAO {

    // Create SQLite table "drivers"

    /**
     *Creates the drivers table in the database
     *
     * @throws SQLException if a database access error occurs
     */
    public void createTable() throws SQLException {
        String sql = """
                    CREATE TABLE IF NOT EXISTS drivers (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        name TEXT NOT NULL,
                        vehicle TEXT NOT NULL
                    );
                """;
        try (Connection conn = DatabaseManager.connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        }
    }

    /**
     * inserting method into the table drivers, with name and vehicle
     *
     * @param name the name of the driver
     * @param vehicle the vehicle of the driver
     * @return the ID of the inserted driver or -1 if insertion fails
     * @throws SQLException if a database access error occurs
     */

    public int insert(String name, String vehicle) throws SQLException {
        String sql = "INSERT INTO drivers(name, vehicle) VALUES(?, ?)";
        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, name);
            pstmt.setString(2, vehicle);
            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
        }
        return -1;
    }
    // Admin method to get all drivers from the SQLite table

    /**
     * Retrieves all drivers from the drivers table
     *
     * @return a list of strings representing a driver, "id: name"
     * @throws SQLException if a database access error occurs
     */
    public List<String> getAll() throws SQLException {
        List<String> list = new ArrayList<>();
        String sql = "SELECT * FROM drivers";
        try (Connection conn = DatabaseManager.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(rs.getInt("id") + ": " + rs.getString("name"));
            }
        }
        return list;
    }
}
