package hibernate.dtos;

import hibernate.entities.HibernateInstructor;
import hibernate.entities.HibernateStudent;
import hibernate.enums.Level;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class CourseDTO {
    private UUID id;
    private String name;
    private Timestamp startDate;
    private Timestamp endDate;
    private Level level;
    private boolean isStarted;

    private HibernateInstructor instructor;
    private Set<HibernateStudent> students;

    public Set<HibernateStudent> getStudents() {
        return students;
    }

    public void setStudents(Set<HibernateStudent> students) {
        this.students = students;
    }




    public CourseDTO() {
    }

    public CourseDTO(UUID id, String name, Timestamp startDate, Timestamp endDate, Level level, boolean isStarted, HibernateInstructor instructor, Set<HibernateStudent> students) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.level = level;
        this.isStarted = isStarted;
        this.instructor = instructor;
        this.students = students;
    }

    // Getters and Setters

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

    public HibernateInstructor getInstructor() {
        return instructor;
    }

    public void setInstructor(HibernateInstructor instructor) {
        this.instructor= instructor;
    }

    // Override toString() for debugging or logging purposes
//    @Override
//    public String toString() {
//        return "CourseDTO{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", startDate=" + startDate +
//                ", endDate=" + endDate +
//                ", level=" + level +
//                ", isStarted=" + isStarted +
//                ", instructor=" + instructor +
//                '}';
//    }
}
