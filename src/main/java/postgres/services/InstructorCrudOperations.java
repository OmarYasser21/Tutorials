package postgres.services;

import postgres.database.DatabaseConnection;
import postgres.entities.Instructor;
import postgres.services.CrudOperations;

import java.util.Map;

public class InstructorCrudOperations extends CrudOperations<Instructor> {
    public InstructorCrudOperations(DatabaseConnection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return "instructor";
    }

    @Override
    public boolean insert(Object... values) {
        // Check if the number of values provided matches the expected number of columns for the 'instructor' table
        if (values.length != 5) {
            throw new IllegalArgumentException("Expected 5 values for instructor insert.");
        }

        // Call the parent insert method with column names and values
        return super.insert("first_name", values[0], "last_name", values[1], "phone_number", values[2],
                "email", values[3], "title", values[4]);
    }

    public boolean updateInstructor(Object id, Map<String, Object> columnValuePairs) {
        return update(id, columnValuePairs);
    }
    public boolean deleteInstructor(Object id) {
        return delete(id);
    }



}
