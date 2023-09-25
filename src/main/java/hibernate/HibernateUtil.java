package hibernate;

import hibernate.entities.HibernateCourse;
import hibernate.entities.HibernateInstructor;
import hibernate.entities.HibernateStudent;
import hibernate.entities.InstructorDetails;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Load Hibernate configuration from hibernate.properties
            Configuration configuration = new Configuration();
            configuration.addAnnotatedClass(HibernateInstructor.class).addAnnotatedClass(HibernateCourse.class).addAnnotatedClass(HibernateStudent.class).addAnnotatedClass(InstructorDetails.class);
            return configuration.buildSessionFactory();
        } catch (Exception ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}
