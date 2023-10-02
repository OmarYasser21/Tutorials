package hibernate.mappers;

import hibernate.dtos.CourseDTO;
import hibernate.dtos.InstructorDTO;
import hibernate.dtos.StudentDTO;
import hibernate.entities.HibernateCourse;
import hibernate.entities.HibernateInstructor;
import hibernate.entities.HibernateStudent;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class StudentMapper {
    public static StudentDTO mapToDTO(HibernateStudent student) {
        if (student == null) {
            return null;
        }

        return new StudentDTO(
                student.getFirstName(),
                student.getLastName(),
                student.getAge(),
                student.getGender(),
                student.getEmail(),
                student.getPhoneNumber(),
                student.getNationalId(),
                student.getCourses()
        );
    }

    public static HibernateStudent mapToEntity(StudentDTO dto) {
        if (dto == null) {
            return null;
        }

        return new HibernateStudent(
                dto.getFirstName(),
                dto.getLastName(),
                dto.getAge(),
                dto.getGender(),
                dto.getEmail(),
                dto.getPhoneNumber(),
                dto.getNationalId(),
                dto.getCourses()
        );
    }

    public static Set<StudentDTO> mapToDTOSet(Set<HibernateStudent> students) {
        if (students == null) {
            return Collections.emptySet();
        }

        return students.stream()
                .map(StudentMapper::mapToDTO)
                .collect(Collectors.toSet());
    }

    public static Set<HibernateStudent> mapToEntitySet(Set<StudentDTO> studentDTOs) {
        if (studentDTOs == null) {
            return Collections.emptySet();
        }

        return studentDTOs.stream()
                .map(StudentMapper::mapToEntity)
                .collect(Collectors.toSet());
    }
}
