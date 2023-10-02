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

        return new InstructorDetailsDTO(
                instructorDetails.getHobbies(),
                instructorDetails.getYoutubeChannel()
        );
    }

    public static InstructorDetails mapToEntity(InstructorDetailsDTO dto) {
        if (dto == null) {
            return null;
        }

        return new InstructorDetails(
                dto.getHobbies(),
                dto.getYoutubeChannel()
        );
    }
}
