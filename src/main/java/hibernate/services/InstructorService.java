package hibernate.services;

import hibernate.HibernateUtil;
import hibernate.dtos.InstructorDTO;
import hibernate.entities.HibernateInstructor;
import hibernate.mappers.InstructorMapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;
import java.util.stream.Collectors;

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
                return InstructorMapper.mapToDTO(instructor);
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

            // Convert Instructor entities to DTOs using the mapper
            return instructors.stream()
                    .map(InstructorMapper::mapToDTO)
                    .collect(Collectors.toList());
        }
    }

    public void close() {
        sessionFactory.close();
    }
}
