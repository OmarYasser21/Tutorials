package postgres.entities;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface EntityMapper<T> {
    T map(ResultSet resultSet) throws SQLException;
}
