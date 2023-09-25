package postgres.entities;

import postgres.enums.Level;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.UUID;

public class Course implements EntityMapper<Course> {
    private UUID id;
    private String name;
    private Timestamp startDate;
    private Timestamp endDate;
    private Level level;
    private boolean isStarted;
    private int instructorId;

    public Course() {}

    public Course(UUID id, String name, Timestamp startDate, Timestamp endDate, Level level, boolean isStarted, int instructorId) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.level = level;
        this.isStarted = isStarted;
        this.instructorId = instructorId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void setStarted(boolean started) {
        isStarted = started;
    }

    public int getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(int instructorId) {
        this.instructorId = instructorId;
    }


    @Override
    public Course map(ResultSet resultSet) throws SQLException {
        // Extract data from the ResultSet and create a Course instance
        return new Course(
                UUID.fromString(resultSet.getString("id")),
                resultSet.getString("name"),
                resultSet.getTimestamp("start_date"),
                resultSet.getTimestamp("end_date"),
                Level.valueOf(resultSet.getString("level")),
                resultSet.getBoolean("is_started"),
                resultSet.getInt("instructor_id")
        );
    }
}
