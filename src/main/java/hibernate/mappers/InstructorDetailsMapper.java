package hibernate.mappers;

import hibernate.dtos.InstructorDTO;
import hibernate.dtos.InstructorDetailsDTO;
import hibernate.entities.HibernateInstructor;
import hibernate.entities.InstructorDetails;

public class InstructorDetailsMapper {
    public static InstructorDetailsDTO mapToDTO(InstructorDetails instructorDetails) {
        if (instructorDetails == null) {
            return null;
        }

        InstructorDetailsDTO dto = new InstructorDetailsDTO();
        dto.setId(instructorDetails.getId());
        dto.setHobbies(instructorDetails.getHobbies());
        dto.setYoutubeChannel(instructorDetails.getYoutubeChannel());
        dto.setInstructor(instructorDetails.getInstructor());

        return dto;
    }

    public static InstructorDetails mapToEntity(InstructorDetailsDTO dto) {
        if (dto == null) {
            return null;
        }

        InstructorDetails entity = new InstructorDetails();
        entity.setId(dto.getId());
        entity.setHobbies(dto.getHobbies());
        entity.setYoutubeChannel(dto.getYoutubeChannel());
        entity.setInstructor(dto.getInstructor());
        return entity;
    }
}
