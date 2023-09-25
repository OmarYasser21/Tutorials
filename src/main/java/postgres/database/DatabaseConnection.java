package postgres.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DatabaseConnection {
    private static final String jdbcUrl = "jdbc:postgresql://localhost:5432/Center Management";
    private static final String username = "postgres";
    private static final String password = "3302";
    private Connection connection;

    public Connection connect() {
        try {
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            if (connection != null) {
                System.out.println("Successfully connected to the database!");
                return connection;
            } else {
                System.out.println("Could not connect to the database!");
                return connection;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to the database", e);
        }
    }

    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connection closed.");
            } catch (SQLException e) {
                throw new RuntimeException("Failed to close the database connection", e);
            }
        }
    }
}