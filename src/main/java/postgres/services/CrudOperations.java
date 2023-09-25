package postgres.services;

import postgres.database.DatabaseConnection;
import postgres.entities.EntityMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public abstract class CrudOperations<T> {
    private final Connection dbConnection;

    public CrudOperations(DatabaseConnection connection) {
        dbConnection = connection.connect();
    }

    public T getById(Object id, EntityMapper<T> mapper) {
        PreparedStatement preparedStatement = null;
        try {
            String sql = "SELECT * FROM " + getTableName() + " WHERE id = ?";
            preparedStatement = dbConnection.prepareStatement(sql);
            preparedStatement.setObject(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return mapper.map(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve " + getTableName() + " by ID", e);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public List<T> getAll(EntityMapper<T> mapper) {
        PreparedStatement preparedStatement = null;
        List<T> entities = new ArrayList<>();
        try {
            String sql = "SELECT * FROM " + getTableName();
            preparedStatement = dbConnection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                entities.add(mapper.map(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve all " + getTableName() + "s", e);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return entities;
    }

    public boolean insert(Object... values) {
        PreparedStatement preparedStatement = null;
        try {
            StringBuilder sqlBuilder = new StringBuilder("INSERT INTO ");
            sqlBuilder.append(getTableName()).append(" (");

            for (int i = 0; i < values.length; i += 2) {
                sqlBuilder.append((String) values[i]).append(", ");
            }

            // Remove the trailing comma and space
            sqlBuilder.setLength(sqlBuilder.length() - 2);

            sqlBuilder.append(") VALUES (");

            for (int i = 0; i < values.length / 2; i++) {
                sqlBuilder.append("?, ");
            }

            // Remove the trailing comma and space
            sqlBuilder.setLength(sqlBuilder.length() - 2);

            sqlBuilder.append(")");

            preparedStatement = dbConnection.prepareStatement(sqlBuilder.toString());

            int parameterIndex = 1;
            for (int i = 1; i < values.length; i += 2) {
                preparedStatement.setObject(parameterIndex++, values[i]);
            }

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println(getTableName() + " added successfully.");
            } else {
                System.out.println("Failed to insert " + getTableName());
            }
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to insert " + getTableName(), e);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean update(Object id, Map<String, Object> columnValuePairs) {
        try {
            // Build the SQL UPDATE statement dynamically based on the provided column-value pairs
            StringBuilder sqlBuilder = new StringBuilder("UPDATE ");
            sqlBuilder.append(getTableName()).append(" SET ");

            for (Map.Entry<String, Object> entry : columnValuePairs.entrySet()) {
                sqlBuilder.append(entry.getKey()).append(" = ?, ");
            }

            // Remove the trailing comma and space
            sqlBuilder.setLength(sqlBuilder.length() - 2);

            // Determine the type of ID and construct the WHERE clause accordingly
            if (id instanceof Integer) {
                sqlBuilder.append(" WHERE id = ?");
            } else if (id instanceof UUID) {
                sqlBuilder.append(" WHERE id = CAST(? AS UUID)");
            } else {
                throw new IllegalArgumentException("Unsupported ID type: " + id.getClass().getName());
            }

            // Create a prepared statement
            PreparedStatement preparedStatement = dbConnection.prepareStatement(sqlBuilder.toString());

            // Set values using the provided arguments
            int parameterIndex = 1;
            for (Map.Entry<String, Object> entry : columnValuePairs.entrySet()) {
                preparedStatement.setObject(parameterIndex++, entry.getValue());
            }

            // Set the ID for the WHERE clause
            preparedStatement.setObject(parameterIndex, id);

            int rowsAffected = preparedStatement.executeUpdate();

            // Check if the update was successful
            if (rowsAffected > 0) {
                System.out.println(getTableName() + " updated successfully.");
            } else {
                System.out.println("Failed to update " + getTableName());
            }
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update " + getTableName(), e);
        }
    }

    public boolean delete(Object id) {
        // Check if the ID is an integer or UUID and construct the SQL query accordingly
        String sql;
        if (id instanceof Integer) {
            sql = "DELETE FROM " + getTableName() + " WHERE id = ?";
        } else if (id instanceof UUID) {
            sql = "DELETE FROM " + getTableName() + " WHERE id = CAST(? AS UUID)";
        } else {
            throw new IllegalArgumentException("Invalid ID type.");
        }

        try (PreparedStatement preparedStatement = dbConnection.prepareStatement(sql)) {
            preparedStatement.setObject(1, id);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println(getTableName() + " deleted successfully.");
            } else {
                System.out.println("Failed to delete " + getTableName() + " by ID");
            }
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete " + getTableName() + " by ID", e);
        }
    }

    protected abstract String getTableName();
}
