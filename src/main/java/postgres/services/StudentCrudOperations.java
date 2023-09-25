package postgres.services;

import postgres.database.DatabaseConnection;
import postgres.entities.Student;
import postgres.services.CrudOperations;

import java.util.Map;

public class StudentCrudOperations extends CrudOperations<Student> {
    public StudentCrudOperations(DatabaseConnection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return "student";
    }

    @Override
    public boolean insert(Object... values) {
        // Check if the number of values provided matches the expected number of columns for the 'instructor' table
        if (values.length != 7) {
            throw new IllegalArgumentException("Expected 7 values for student insert.");
        }

        // Call the parent insert method with column names and values
        return super.insert("first_name", values[0], "last_name", values[1], "age", values[2],
                "gender", values[3], "email", values[4], "phone_number", values[5], "national_id", values[6]);
    }
    public boolean updateStudent(Object id, Map<String, Object> columnValuePairs) {
        return update(id, columnValuePairs);
    }

    public boolean deleteStudent(Object id) {
        return delete(id);
    }


}
