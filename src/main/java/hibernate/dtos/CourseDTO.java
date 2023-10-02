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

    private InstructorDTO instructor;
    private Set<StudentDTO> students;

    public Set<StudentDTO> getStudents() {
        return students;
    }

    public void setStudents(Set<StudentDTO> students) {
        this.students = students;
    }




    public CourseDTO() {
    }

    public CourseDTO(String name, Timestamp startDate, Timestamp endDate, Level level, boolean isStarted, InstructorDTO instructor, Set<StudentDTO> students) {
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

    public InstructorDTO getInstructor() {
        return instructor;
    }

    public void setInstructor(InstructorDTO instructor) {
        this.instructor= instructor;
    }

    public static void printCourse(CourseDTO dto) {
        System.out.println("Course Details:");
        System.out.println("Course Name: " + dto.getName());
        System.out.println("Start Date: " + dto.getStartDate());
        System.out.println("End Date: " + dto.getEndDate());
        System.out.println("Level: " + dto.getLevel());
        System.out.println("Is Started: " + dto.isStarted());

        // Print associated instructor if available
        InstructorDTO instructor = dto.getInstructor();
        if (instructor != null) {
            System.out.println("Instructor: " + instructor.getFirstName() + " " + instructor.getLastName());
        } else {
            System.out.println("No instructor assigned to this course.");
        }

        // Print associated students
        Set<StudentDTO> students = dto.getStudents();
        if (students != null && !students.isEmpty()) {
            // Create a StringBuilder to build the comma-separated student names
            StringBuilder studentNames = new StringBuilder();

            for (StudentDTO student : students) {
                // Append the student name to the StringBuilder
                studentNames.append(student.getFirstName()).append(" ").append(student.getLastName()).append(", ");
            }

            // Remove the trailing ", " and print the student names
            if (!studentNames.isEmpty()) {
                studentNames.setLength(studentNames.length() - 2); // Remove the last ", "
                System.out.println("Enrolled Students: " + studentNames.toString());
            }
        } else {
            System.out.println("No students enrolled in this course.");
        }

        System.out.println();
    }

}
