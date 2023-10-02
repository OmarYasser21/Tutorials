package hibernate;

import hibernate.dtos.CourseDTO;
import hibernate.dtos.InstructorDTO;
import hibernate.dtos.InstructorDetailsDTO;
import hibernate.dtos.StudentDTO;
import hibernate.entities.HibernateCourse;
import hibernate.entities.HibernateInstructor;
import hibernate.entities.HibernateStudent;
import hibernate.entities.InstructorDetails;
import hibernate.enums.Gender;
import hibernate.enums.Level;
import hibernate.mappers.InstructorMapper;
import hibernate.services.CourseService;
import hibernate.services.InstructorDetailsService;
import hibernate.services.InstructorService;
import hibernate.services.StudentService;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class HibernateMain {
    public static void main(String[] args) throws SQLException {
          InstructorService instructorService = new InstructorService();
          CourseService courseService = new CourseService();
          StudentService studentService = new StudentService();
          InstructorDetailsService instructorDetailsService = new InstructorDetailsService();

        System.out.println(courseService.getStudentsEnrolledInIntermediateCourses());
//          List<String> instructorAndStudents = instructorService.getInstructorAndStudents();
//
//          if (!instructorAndStudents.isEmpty()) {
//                System.out.println("Instructors and Their Students:");
//                for (String info : instructorAndStudents) {
//                      System.out.println(info);
//                }
//          } else {
//                System.out.println("No instructor and student data found.");
//          }

//          List<InstructorDTO> instructorDTOs = instructorService.getInstructorsWithCourses();
//
//          for (InstructorDTO instructorDTO : instructorDTOs) {
//                String instructorName = instructorDTO.getFirstName() + " " + instructorDTO.getLastName();
//                List<String> courseNames = instructorDTO.getCourses().stream()
//                        .map(CourseDTO::getName)
//                        .collect(Collectors.toList());
//
//                System.out.println("Instructor: " + instructorName);
//                System.out.println("Courses Taught: " + String.join(", ", courseNames));
//                System.out.println(); // Add a blank line between instructors
//          }

//          List<String> courseDetails = courseService.getCourseNameStartDateAndStudents();

//           Display the course details
//          System.out.println("Course Details:");
//          System.out.println();
//          for (String courseDetail : courseDetails) {
//                System.out.println(courseDetail);
//          }


          instructorService.close();
          courseService.close();
          studentService.close();
          instructorDetailsService.close();
    }
}