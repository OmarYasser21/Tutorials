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
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

    public CourseDTO getCourseById(UUID courseId) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            HibernateCourse course = session.get(HibernateCourse.class, courseId);
            session.getTransaction().commit();

            if (course != null) {
                return CourseMapper.mapToDTO(course);
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

    public List<CourseDTO> getAllCourses() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            List<HibernateCourse> courses = session.createQuery("FROM HibernateCourse", HibernateCourse.class).list();
            session.getTransaction().commit();

            return courses.stream()
                    .map(CourseMapper::mapToDTO)
                    .collect(Collectors.toList());

        }
    }

    public void close() {
        HibernateUtil.shutdown();
    }
}
