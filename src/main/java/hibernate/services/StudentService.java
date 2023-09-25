package hibernate.services;

import hibernate.dtos.StudentDTO;
import hibernate.entities.HibernateStudent;
import hibernate.enums.Gender;
import hibernate.mappers.StudentMapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import hibernate.HibernateUtil;
import org.hibernate.query.Query;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class StudentService {
    private final SessionFactory sessionFactory;

    public StudentService() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public void addStudent(StudentDTO dto) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.save(StudentMapper.mapToEntity(dto));
            session.getTransaction().commit();
        }
    }

    public void updateStudent(UUID studentId, String newFirstName, String newLastName, int newAge, Gender newGender, String newEmail, String newPhoneNumber, Long newNationalId) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Query<HibernateStudent> query = session.createQuery("FROM HibernateStudent WHERE id = :studentId", HibernateStudent.class);
            query.setParameter("studentId", studentId);
            HibernateStudent student = query.uniqueResult();

            if (student != null) {
                if (newFirstName != null) {
                    student.setFirstName(newFirstName);
                }
                if (newLastName != null) {
                    student.setLastName(newLastName);
                }
                if (newAge != 0) {
                    student.setAge(newAge);
                }
                if (newGender != null) {
                    student.setGender(newGender);
                }
                if (newEmail != null) {
                    student.setEmail(newEmail);
                }
                if (newPhoneNumber != null) {
                    student.setPhoneNumber(newPhoneNumber);
                }
                if (newNationalId != null) {
                    student.setNationalId(newNationalId);
                }
                session.update(student);
                session.getTransaction().commit();
            } else {
                throw new RuntimeException("Student with ID " + studentId + " not found.");
            }
        }
    }

    public StudentDTO getStudentById(UUID studentId) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            HibernateStudent student = session.get(HibernateStudent.class, studentId);
            session.getTransaction().commit();

            if (student != null) {
                return StudentMapper.mapToDTO(student);
            } else {
                throw new RuntimeException("Student with ID " + studentId + " not found.");
            }
        }
    }

    public void deleteStudent(UUID studentId) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            HibernateStudent student = session.get(HibernateStudent.class, studentId);
            if (student != null) {
                session.delete(student);
            }
            session.getTransaction().commit();
        }
    }

    public List<StudentDTO> getAllStudents() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            List<HibernateStudent> students = session.createQuery("FROM HibernateStudent", HibernateStudent.class).list();
            session.getTransaction().commit();

            return students.stream()
                    .map(StudentMapper::mapToDTO)
                    .collect(Collectors.toList());

        }
    }

    public void close() {
        HibernateUtil.shutdown();
    }
}
