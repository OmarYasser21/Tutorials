package hibernate.mappers;

import hibernate.dtos.InstructorDTO;
import hibernate.dtos.StudentDTO;
import hibernate.entities.HibernateInstructor;
import hibernate.entities.HibernateStudent;
public class StudentMapper {
    public static StudentDTO mapToDTO(HibernateStudent student) {
        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setFirstName(student.getFirstName());
        dto.setLastName(student.getLastName());
        dto.setAge(student.getAge());
        dto.setGender(student.getGender());
        dto.setEmail(student.getEmail());
        dto.setPhoneNumber(student.getPhoneNumber());
        dto.setNationalId(student.getNationalId());
        dto.setCourses(student.getCourses());
        return dto;
    }

    public static HibernateStudent mapToEntity(StudentDTO dto) {
        if (dto == null) {
            return null;
        }

        HibernateStudent entity = new HibernateStudent();
        entity.setId(dto.getId());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setAge(dto.getAge());
        entity.setGender(dto.getGender());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setEmail(dto.getEmail());
        entity.setNationalId(dto.getNationalId());
        entity.setCourses(dto.getCourses());
        return entity;
    }
}
