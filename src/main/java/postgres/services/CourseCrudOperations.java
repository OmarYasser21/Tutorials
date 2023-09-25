package postgres.services;

import postgres.database.DatabaseConnection;
import postgres.entities.Course;

import java.util.Map;

public class CourseCrudOperations extends CrudOperations<Course> {
    public CourseCrudOperations(DatabaseConnection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return "course";
    }

    @Override
    public boolean insert(Object... values) {
        // Check if the number of values provided matches the expected number of columns for the 'instructor' table
        if (values.length != 6) {
            throw new IllegalArgumentException("Expected 6 values for instructor insert.");
        }

        // Call the parent insert method with column names and values
        return super.insert("name", values[0], "start_date", values[1], "end_date", values[2],
                "level", values[3], "is_started", values[4], "instructor_id", values[5]);
    }
    public boolean updateCourse(Object id, Map<String, Object> columnValuePairs) {
        return update(id, columnValuePairs);
    }

    public boolean deleteCourse(Object id) {
        return delete(id);
    }

}
