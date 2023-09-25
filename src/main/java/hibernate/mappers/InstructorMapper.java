package hibernate.mappers;

import hibernate.dtos.InstructorDTO;
import hibernate.entities.HibernateInstructor;

public class InstructorMapper {
    public static InstructorDTO mapToDTO(HibernateInstructor instructor) {
        InstructorDTO dto = new InstructorDTO();
        dto.setId(instructor.getId());
        dto.setFirstName(instructor.getFirstName());
        dto.setLastName(instructor.getLastName());
        dto.setPhoneNumber(instructor.getPhoneNumber());
        dto.setEmail(instructor.getEmail());
        dto.setTitle(instructor.getTitle());
        dto.setInstructorDetails(instructor.getInstructorDetails());
        dto.setCourses(instructor.getCourses());
        return dto;
    }

    public static HibernateInstructor mapToEntity(InstructorDTO dto) {
        if (dto == null) {
            return null;
        }

        HibernateInstructor entity = new HibernateInstructor();
        entity.setId(dto.getId());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setEmail(dto.getEmail());
        entity.setTitle(dto.getTitle());
        return entity;
    }
}
