package hibernate.services;

import hibernate.HibernateUtil;
import hibernate.dtos.CourseDTO;
import hibernate.dtos.InstructorDTO;
import hibernate.entities.HibernateCourse;
import hibernate.entities.HibernateInstructor;
import hibernate.mappers.CourseMapper;
import hibernate.mappers.InstructorMapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static hibernate.dtos.InstructorDTO.printInstructor;

public class InstructorService {
    private final SessionFactory sessionFactory;

    public InstructorService() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public void addInstructor(InstructorDTO dto) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.save(InstructorMapper.mapToEntity(dto));
            session.getTransaction().commit();
        }
    }

    public void updateInstructor(int instructorId, String newFirstName, String newLastName, String newPhoneNumber, String newEmail, String newTitle) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Query<HibernateInstructor> query = session.createQuery("FROM HibernateInstructor WHERE id = :instructorId", HibernateInstructor.class);
            query.setParameter("instructorId", instructorId);
            HibernateInstructor instructor = query.uniqueResult();

            if (instructor != null) {
                if (newFirstName != null) {
                    instructor.setFirstName(newFirstName);
                }
                if (newLastName != null) {
                    instructor.setLastName(newLastName);
                }
                if (newPhoneNumber != null) {
                    instructor.setPhoneNumber(newPhoneNumber);
                }
                if (newEmail != null) {
                    instructor.setEmail(newEmail);
                }
                if (newTitle != null) {
                    instructor.setTitle(newTitle);
                }

                session.update(instructor);
                session.getTransaction().commit();
            } else {
                throw new RuntimeException("Instructor with ID " + instructorId + " not found.");
            }
        }
    }

    public InstructorDTO getInstructorById(int id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Query<HibernateInstructor> query = session.createQuery("FROM HibernateInstructor WHERE id = :instructorId", HibernateInstructor.class);
            query.setParameter("instructorId", id);
            HibernateInstructor instructor = query.uniqueResult();
            session.getTransaction().commit();

            if (instructor != null) {
                InstructorDTO instructorDTO = InstructorMapper.mapToDTO(instructor);
                instructorDTO.setCourses(CourseMapper.mapToDTOList(instructor.getCourses()));
                // Call the printInstructor method to print the instructor details
                printInstructor(instructorDTO);
                return instructorDTO;
            } else {
                throw new RuntimeException("Instructor with ID " + id + " not found.");
            }
        }
    }

    public void deleteInstructor(int id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Query<HibernateInstructor> query = session.createQuery("FROM HibernateInstructor WHERE id = :instructorId", HibernateInstructor.class);
            query.setParameter("instructorId", id);
            HibernateInstructor instructor = query.uniqueResult();

            if (instructor != null) {
                session.delete(instructor);
            }
            session.getTransaction().commit();
        }
    }

    public List<InstructorDTO> getAllInstructors() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Query<HibernateInstructor> query = session.createQuery("FROM HibernateInstructor", HibernateInstructor.class);
            List<HibernateInstructor> instructors = query.list();
            session.getTransaction().commit();

            List<InstructorDTO> instructorDTOs = instructors.stream()
                    .map(instructor -> {
                        InstructorDTO instructorDTO = InstructorMapper.mapToDTO(instructor);
                        instructorDTO.setCourses(CourseMapper.mapToDTOList(instructor.getCourses()));
                        return instructorDTO;
                    })
                    .collect(Collectors.toList());

            // Call the printInstructor method to print each instructor's details
            System.out.println("All Instructors: ");
            System.out.println();
            for (InstructorDTO instructorDTO : instructorDTOs) {
                printInstructor(instructorDTO);
            }

            return instructorDTOs;
        }
    }



    public List<String> getInstructorNamesByTitle(String title) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Query<String> query = session.createQuery("SELECT CONCAT(i.firstName, ' ', i.lastName) FROM HibernateInstructor i WHERE i.title = :title", String.class);
            query.setParameter("title", title);
            List<String> instructorNames = query.list();
            session.getTransaction().commit();

            return instructorNames;
        }
    }

    public String getInstructorNameByCourseName(String courseName) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Query<String> query = session.createQuery(
                    "SELECT CONCAT (c.instructor.firstName, ' ', c.instructor.lastName) FROM HibernateCourse c WHERE c.name = :courseName",
                    String.class
            );
            query.setParameter("courseName", courseName);
            String instructorName = query.uniqueResult();
            session.getTransaction().commit();

            if (instructorName != null) {
                return instructorName;
            } else {
                throw new RuntimeException("Instructor for course " + courseName + " not found.");
            }
        }
    }


    public List<String> getStudentNamesByInstructor(String instructorName) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            // Retrieve the instructor by name
            Query<HibernateInstructor> instructorQuery = session.createQuery(
                    "FROM HibernateInstructor WHERE CONCAT(firstName, ' ', lastName) = :instructorName",
                    HibernateInstructor.class
            );
            instructorQuery.setParameter("instructorName", instructorName);

            HibernateInstructor instructor = instructorQuery.uniqueResult();

            if (instructor == null) {
                throw new RuntimeException("Instructor not found with the given name: " + instructorName);
            }

            // Get the courses taught by the instructor
            List<HibernateCourse> courses = instructor.getCourses();

            // Collect students from those courses
            List<String> studentNames = courses.stream()
                    .flatMap(course -> course.getStudents().stream())
                    .map(student -> student.getFirstName() + " " + student.getLastName())
                    .distinct()
                    .collect(Collectors.toList());

            session.getTransaction().commit();
            return studentNames;
        }
    }

    public List<String> getCourseNamesByInstructor(String instructorName) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Query<String> courseQuery = session.createQuery(
                    "SELECT  c.name FROM HibernateCourse c WHERE c.instructor.firstName || ' ' || c.instructor.lastName = :instructorName",
                    String.class
            );
            courseQuery.setParameter("instructorName", instructorName);

            List<String> courseNames = courseQuery.list();
           // String commaSeparatedNames = String.join(", ", courseNames);

            session.getTransaction().commit();
            return courseNames;
        }
    }

    public List<InstructorDTO> getInstructorsWithCourses() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Query<HibernateInstructor> query = session.createQuery("FROM HibernateInstructor i LEFT JOIN FETCH i.courses", HibernateInstructor.class);
            List<HibernateInstructor> instructors = query.list();

            session.getTransaction().commit();

            // Convert Hibernate entities to DTOs
            List<InstructorDTO> instructorDTOs = instructors.stream()
                    .map(instructor -> {
                        InstructorDTO dto = InstructorMapper.mapToDTO(instructor);
                        List<CourseDTO> courses = instructor.getCourses().stream()
                                .map(CourseMapper::mapToDTO)
                                .collect(Collectors.toList());
                        dto.setCourses(courses);
                        return dto;
                    })
                    .collect(Collectors.toList());

            return instructorDTOs;
        }
    }

    public List<String> getInstructorAndStudents() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Query<Object[]> query = session.createQuery(
                    "SELECT DISTINCT i.firstName, i.lastName, s.firstName, s.lastName " +
                            "FROM HibernateInstructor i " +
                            "JOIN i.courses c " +
                            "JOIN c.students s",
                    Object[].class
            );

            List<Object[]> results = query.list();

            session.getTransaction().commit();

            List<String> instructorAndStudents = new ArrayList<>();
            Map<String, List<String>> instructorToStudentsMap = new HashMap<>();

            for (Object[] result : results) {
                String instructorName = result[0] + " " + result[1];
                String studentName = result[2] + " " + result[3];

                if (!instructorToStudentsMap.containsKey(instructorName)) {
                    instructorToStudentsMap.put(instructorName, new ArrayList<>());
                }

                // Add student name to the instructor's list of students
                instructorToStudentsMap.get(instructorName).add(studentName);
            }

            // Create the final list of instructor and student entries
            for (Map.Entry<String, List<String>> entry : instructorToStudentsMap.entrySet()) {
                String instructorEntry = "Instructor: " + entry.getKey();
                instructorAndStudents.add(instructorEntry);

                List<String> students = entry.getValue();
                if (!students.isEmpty()) {
                    String studentsEntry = "Students: " + String.join(", ", students);
                    instructorAndStudents.add(studentsEntry);
                }

                // Add a blank line between instructors
                instructorAndStudents.add("");
            }

            return instructorAndStudents;
        }
    }




    public void close() {
        sessionFactory.close();
    }
}
