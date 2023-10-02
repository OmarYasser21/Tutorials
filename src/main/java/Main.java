import hibernate.dtos.InstructorDTO;
import hibernate.services.InstructorService;
import postgres.entities.Course;
import postgres.entities.EntityMapper;
import postgres.entities.Instructor;
import postgres.entities.Student;
import postgres.enums.Gender;
import postgres.enums.Level;
import postgres.services.CourseCrudOperations;
import postgres.services.InstructorCrudOperations;
import postgres.services.StudentCrudOperations;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Main {
    public static void main(String[] args) throws SQLException {

      InstructorService instructorService = new InstructorService();

    }


    //        DatabaseConnection databaseConnection = new DatabaseConnection();
//        InstructorCrudOperations instructorCrudOperations = new InstructorCrudOperations(databaseConnection);
//        StudentCrudOperations studentCrudOperations = new StudentCrudOperations(databaseConnection);
//        CourseCrudOperations courseCrudOperations = new CourseCrudOperations(databaseConnection);
//        EntityMapper<Student> studentMapper = new Student();
//        EntityMapper<Course> courseMapper = new Course();
//        EntityMapper<Instructor> instructorMapper = new Instructor();
//        //performInstructorCrud(instructorCrudOperations, instructorMapper);
//        //performCourseCRUD(courseCrudOperations, courseMapper);
//        //performStudentCRUD(studentCrudOperations, studentMapper);
//        JoinQueries joinQueries = new JoinQueries(databaseConnection);
//        System.out.println();
//        joinQueries.getInstructorCourseDetails();
//
//
//        databaseConnection.disconnect();
    private static void performInstructorCrud(InstructorCrudOperations instructorCrudOperations, EntityMapper<Instructor> instructorMapper){
        //Insert instructor
         instructorCrudOperations.insert("Mahmoud", "Hassan", "mahmoudh@hotmail.com", "353322136", "Research Assistant");

         //Update instructor
         Map<String, Object> updateData = new HashMap<>();
         updateData.put("first_name", "Amr");
         updateData.put("last_name", "Samy");
         instructorCrudOperations.updateInstructor(6, updateData);

         //Delete instructor
         instructorCrudOperations.delete(7);

        //Get instructor by id
        Instructor instructor= instructorCrudOperations.getById( 1, instructorMapper);
        if (instructor != null) {
            System.out.println("Instructor Details:");
            System.out.println("ID: " + instructor.getId());
            System.out.println("First Name: " + instructor.getFirstName());
            System.out.println("Last Name: " + instructor.getLastName());
            System.out.println("Email: " + instructor.getEmail());
            System.out.println("Phone Number: " + instructor.getPhoneNumber());
            System.out.println("Title: " + instructor.getTitle());
        } else {
            System.out.println("Instructor not found.");
        }
        System.out.println();
        //Get all instructors
        List<Instructor> instructors = instructorCrudOperations.getAll( instructorMapper);
        if (!instructors.isEmpty()) {
            System.out.println("All Instructors:");
            for (Instructor inst : instructors) {
                System.out.println("ID: " + inst.getId());
                System.out.println("First Name: " + inst.getFirstName());
                System.out.println("Last Name: " + inst.getLastName());
                System.out.println("Email: " + inst.getEmail());
                System.out.println("Phone Number: " + inst.getPhoneNumber());
                System.out.println("Title: " + inst.getTitle());
                System.out.println();
            }
        } else {
            System.out.println("No instructors found.");
        }
        System.out.println();
    }

    private static void performCourseCRUD(CourseCrudOperations courseCrudOperations, EntityMapper<Course> courseMapper) {
        // Insert a course
        courseCrudOperations.insert("SE", Timestamp.valueOf("2023-09-10 10:00:00"),
                Timestamp.valueOf("2023-12-15 14:30:00"),
                Level.advanced.name(),
                true,
                1
        );


        // Update a course
        Map<String, Object> updateData = new HashMap<>();
        updateData.put("name", "Networks");
        courseCrudOperations.updateCourse(UUID.fromString("45ffad28-ce72-462e-93ee-39511b9f645c"), updateData);

        // Delete a course
         courseCrudOperations.delete(UUID.fromString("45ffad28-ce72-462e-93ee-39511b9f645c"));

        // Get a course by ID
        Course course = courseCrudOperations.getById(UUID.fromString("1763464c-89e8-4cd7-ae63-198e9614e84e"), courseMapper);
        if (course != null) {
            System.out.println("Course Details:");
            System.out.println("ID: " + course.getId());
            System.out.println("Name: " + course.getName());
            System.out.println("Start Date: " + course.getStartDate());
            System.out.println("End Date: " + course.getEndDate());
            System.out.println("Level: " + course.getLevel());
            System.out.println("Is Started: " + course.isStarted());
            System.out.println("Instructor ID: " + course.getInstructorId());
        } else {
            System.out.println("Course not found.");
        }

        // Get all courses
         List<Course> courses = courseCrudOperations.getAll(courseMapper);
        if(!courses.isEmpty()){
        System.out.println("All courses");
        for (Course c: courses) {
            System.out.println("ID: " + c.getId());
            System.out.println("Name: " + c.getName());
            System.out.println("Start Date: " + c.getStartDate());
            System.out.println("End Date: " + c.getEndDate());
            System.out.println("Level: " + c.getLevel());
            System.out.println("Is Started: " + c.isStarted());
            System.out.println("Instructor ID: " + c.getInstructorId());
            System.out.println();
        }
    }
    }

    private static void performStudentCRUD(StudentCrudOperations studentCrudOperations, EntityMapper<Student> studentMapper){
        // Insert a student
        studentCrudOperations.insert(
                "Michael",
                "Angelo",
                24,
                Gender.male.name(),
                "mangelo@gmail.com",
                "010044335643",
                588920108
        );


        // Update a student
        Map<String, Object> updateData = new HashMap<>();
        updateData.put("phone_number", "01111111111");
        studentCrudOperations.updateStudent(UUID.fromString("94aaec5a-aba5-409e-922f-052ce5c019fa"), updateData);

        // Delete a student
        studentCrudOperations.deleteStudent(UUID.fromString("94aaec5a-aba5-409e-922f-052ce5c019fa"));


        // Get a student by ID
        Student student = studentCrudOperations.getById(UUID.fromString("6c8b2545-c7d7-45b9-ac3b-1b33a7654e57"), studentMapper);
        if (student != null) {
            System.out.println("Student Details:");
            System.out.println("ID: " + student.getId());
            System.out.println("First Name: " + student.getFirstName());
            System.out.println("Last Name: " + student.getLastName());
            System.out.println("Email: " + student.getEmail());
            System.out.println("Phone Number: " + student.getPhoneNumber());
            System.out.println("Age: " + student.getAge());
            System.out.println("National ID: " + student.getNationalId());
            System.out.println("Gender: " + student.getGender());
        } else {
            System.out.println("Student not found.");
        }

        // Get all students
        List<Student> students = studentCrudOperations.getAll(studentMapper);
        if (!students.isEmpty()) {
            System.out.println("All Students:");
            for (Student s : students) {
                System.out.println("ID: " + s.getId());
                System.out.println("First Name: " + s.getFirstName());
                System.out.println("Last Name: " + s.getLastName());
                System.out.println("Age: " + s.getAge());
                System.out.println("Gender: " + s.getGender());
                System.out.println("Email: " + s.getEmail());
                System.out.println("Phone Number: " + s.getPhoneNumber());
                System.out.println("National ID: " + s.getNationalId());
                System.out.println();
            }
        } else {
            System.out.println("No students found.");
        }
    }
}
