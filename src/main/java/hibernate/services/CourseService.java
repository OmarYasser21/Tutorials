package hibernate.services;

import hibernate.dtos.CourseDTO;
import hibernate.entities.HibernateCourse;
import hibernate.entities.HibernateInstructor;
import hibernate.enums.Level;
import hibernate.mappers.CourseMapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import hibernate.HibernateUtil;
import org.hibernate.query.Query;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

import static hibernate.dtos.CourseDTO.printCourse;

public class CourseService {
    private final SessionFactory sessionFactory;

    public CourseService() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public void addCourse(CourseDTO dto) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.save(CourseMapper.mapToEntity(dto));
            session.getTransaction().commit();
        }
    }

    public void updateCourse(UUID courseId, String newName, Timestamp newStartDate, Timestamp newEndDate, Level newLevel, boolean newIsStarted, HibernateInstructor instructor) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Query<HibernateCourse> query = session.createQuery("FROM HibernateCourse WHERE id = :courseId", HibernateCourse.class);
            query.setParameter("courseId", courseId);
            HibernateCourse course = query.uniqueResult();

            if (course != null) {
                if (newName != null) {
                    course.setName(newName);
                }
                if (newStartDate != null) {
                    course.setStartDate(newStartDate);
                }
                if (newEndDate != null) {
                    course.setEndDate(newEndDate);
                }
                if (newLevel != null) {
                    course.setLevel(newLevel);
                }
                course.setStarted(newIsStarted);
                course.setInstructor(instructor);

                session.update(course);
                session.getTransaction().commit();
            } else {
                throw new RuntimeException("Course with ID " + courseId + " not found.");
            }
        }
    }

    public void getCourseById(UUID courseId) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            HibernateCourse course = session.get(HibernateCourse.class, courseId);
            session.getTransaction().commit();

            if (course != null) {
                CourseDTO dto = CourseMapper.mapToDTO(course);
                printCourse(dto); // Print the course details
            } else {
                throw new RuntimeException("Course with ID " + courseId + " not found.");
            }
        }
    }

    public void deleteCourse(UUID courseId) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            HibernateCourse course = session.get(HibernateCourse.class, courseId);
            if (course != null) {
                session.delete(course);
            }
            session.getTransaction().commit();
        }
    }

    public void getAllCourses() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            List<HibernateCourse> courses = session.createQuery("FROM HibernateCourse", HibernateCourse.class).list();
            session.getTransaction().commit();

            List<CourseDTO> courseDTOs = courses.stream()
                    .map(CourseMapper::mapToDTO)
                    .toList();

            // Print the details for each course
            System.out.println("All Courses:");
            System.out.println();
            for (CourseDTO courseDTO : courseDTOs) {
                printCourse(courseDTO); // Print each course details
            }

        }
    }


    public List<String> getStudentsEnrolledInIntermediateCourses() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            String hql = "SELECT c.name, " +
                    "       STRING_AGG(CONCAT(s.firstName, ' ', s.lastName), ', ') " +
                    "FROM HibernateCourse c " +
                    "JOIN c.students s " +
                    "WHERE c.level = intermediate " +
                    "GROUP BY c.name";

            Query<Object[]> query = session.createQuery(hql, Object[].class);

            List<Object[]> courseAndStudentNames = query.list();

            List<String> result = new ArrayList<>();
            System.out.println("Students Enrolled in Intermediate Courses: ");
            for (Object[] courseData : courseAndStudentNames) {
                String courseName = (String) courseData[0];
                String studentNames = (String) courseData[1];
                result.add("Course Name: " + courseName);
                result.add("Students Enrolled: " + studentNames + "\n");
            }

            session.getTransaction().commit();
            return result;
        }
    }


    public List<String> getCourseNameStartDateAndStudents() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Query<Object[]> query = session.createQuery(
                    "SELECT c.name, c.startDate, s.firstName, s.lastName " +
                            "FROM HibernateCourse c " +
                            "LEFT JOIN c.students s",
                    Object[].class
            );

            List<Object[]> results = query.list();

            session.getTransaction().commit();

            Map<String, List<String>> courseDetailsMap = new LinkedHashMap<>();

            for (Object[] result : results) {
                String courseName = (String) result[0];
                Timestamp startDate = (Timestamp) result[1];
                String studentFirstName = (String) result[2];
                String studentLastName = (String) result[3];

                courseDetailsMap.computeIfAbsent(courseName, k -> new ArrayList<>());

                if (!courseDetailsMap.get(courseName).contains("Course: " + courseName)) {
                    courseDetailsMap.get(courseName).add("Course: " + courseName);
                    courseDetailsMap.get(courseName).add("Start Date: " + startDate);
                    courseDetailsMap.get(courseName).add("Students Enrolled:");
                }

                if (studentFirstName != null && studentLastName != null) {
                    courseDetailsMap.get(courseName).add(studentFirstName + " " + studentLastName);
                }
            }

            List<String> courseDetails = new ArrayList<>();

            for (Map.Entry<String, List<String>> entry : courseDetailsMap.entrySet()) {
                courseDetails.addAll(entry.getValue());
                courseDetails.add(""); // Add a blank line
            }

            return courseDetails;
        }
    }








    public void close() {
        HibernateUtil.shutdown();
    }
}
