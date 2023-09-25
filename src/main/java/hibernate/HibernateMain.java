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
import java.util.List;
import java.util.UUID;

public class HibernateMain {
    public static void main(String[] args) throws SQLException {
          InstructorService instructorService = new InstructorService();
          CourseService courseService = new CourseService();
          StudentService studentService = new StudentService();
          InstructorDetailsService instructorDetailsService = new InstructorDetailsService();

        System.out.println(courseService.getCourseById(UUID.fromString("029306c3-46fe-400e-9826-d9ba2c482cda")));


          instructorService.close();
          courseService.close();
          studentService.close();
          instructorDetailsService.close();
    }
}