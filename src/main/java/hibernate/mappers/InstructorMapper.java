package hibernate.mappers;

import hibernate.dtos.InstructorDTO;
import hibernate.entities.HibernateInstructor;

import java.util.Collections;

public class InstructorMapper {
    public static InstructorDTO mapToDTO(HibernateInstructor instructor) {
        return new InstructorDTO(
                instructor.getFirstName(),
                instructor.getLastName(),
                instructor.getPhoneNumber(),
                instructor.getEmail(),
                instructor.getTitle(),
                InstructorDetailsMapper.mapToDTO(instructor.getInstructorDetails())
        );
    }


    public static HibernateInstructor mapToEntity(InstructorDTO dto) {
        if (dto == null) {
            return null;
        }
        return new HibernateInstructor(
                dto.getFirstName(),
                dto.getLastName(),
                dto.getPhoneNumber(),
                dto.getEmail(),
                dto.getTitle(),
                InstructorDetailsMapper.mapToEntity(dto.getInstructorDetails())
        );
    }
}
