/**
 *
 * Documentation: HistoryDatabase
 *
 * Manages the SQLite connection for the order history database
 *
 * Module Purpose:
 * - Provides a way to connect to the history.db SQLite database
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HistoryDatabase {
    private static final String URL = "jdbc:sqlite:history.db";

    /**
     * Establishes a connection to the history SQLite database
     *
     * @return connection object to the database
     * @throws SQLException if a database access error occurs
     */

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}
