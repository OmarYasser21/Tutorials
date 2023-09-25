package hibernate.services;

import hibernate.dtos.InstructorDetailsDTO;
import hibernate.entities.HibernateInstructor;
import hibernate.entities.InstructorDetails;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import hibernate.HibernateUtil;
import hibernate.mappers.InstructorDetailsMapper;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class InstructorDetailsService {
    private final SessionFactory sessionFactory;

    public InstructorDetailsService() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public void addInstructorDetails(InstructorDetailsDTO dto) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.save(InstructorDetailsMapper.mapToEntity(dto));
            session.getTransaction().commit();
        }
    }

    public InstructorDetailsDTO getInstructorDetailsById(UUID instructorDetailsId) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            InstructorDetails instructorDetails = session.get(InstructorDetails.class, instructorDetailsId);
            session.getTransaction().commit();

            return InstructorDetailsMapper.mapToDTO(instructorDetails);
        }
    }

    public void updateInstructorDetails(UUID id, String newHobbies, String newYoutubeChannel, HibernateInstructor instructor) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            InstructorDetails instructorDetails = session.get(InstructorDetails.class, id);

            if (instructorDetails != null) {
                if (newHobbies != null) {
                    instructorDetails.setHobbies(newHobbies);
                }
                if (newYoutubeChannel != null) {
                    instructorDetails.setYoutubeChannel(newYoutubeChannel);
                }
                if(instructor != null){
                    instructorDetails.setInstructor(instructor);
                }

                session.update(instructorDetails);
                session.getTransaction().commit();
            } else {
                throw new RuntimeException("InstructorDetails with ID " + id + " not found.");
            }
        }
    }



    public void deleteInstructorDetails(UUID instructorDetailsId) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            InstructorDetails instructorDetails = session.get(InstructorDetails.class, instructorDetailsId);
            if (instructorDetails != null) {
                session.delete(instructorDetails);
            }
            session.getTransaction().commit();
        }
    }

    public List<InstructorDetailsDTO> getAllInstructorDetails() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            List<InstructorDetails> instructorDetailsList = session.createQuery("FROM InstructorDetails", InstructorDetails.class).list();
            session.getTransaction().commit();

            return instructorDetailsList.stream()
                    .map(InstructorDetailsMapper::mapToDTO)
                    .collect(Collectors.toList());
        }
    }

    public void close() {
        HibernateUtil.shutdown();
    }
}
