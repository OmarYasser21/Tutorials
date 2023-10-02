package hibernate.mappers;

import hibernate.dtos.CourseDTO;
import hibernate.dtos.InstructorDTO;
import hibernate.entities.HibernateCourse;
import hibernate.entities.HibernateInstructor;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CourseMapper {
    public static CourseDTO mapToDTO(HibernateCourse course) {
        return new CourseDTO(course.getName(), course.getStartDate(), course.getEndDate(),
                course.getLevel(), course.isStarted(), InstructorMapper.mapToDTO(course.getInstructor()), StudentMapper.mapToDTOSet(course.getStudents()));
    }

    public static HibernateCourse mapToEntity(CourseDTO dto) {
        if (dto == null) {
            return null;
        }
        return new HibernateCourse(dto.getName(), dto.getStartDate(), dto.getEndDate(), dto.getLevel(),
                dto.isStarted(), InstructorMapper.mapToEntity(dto.getInstructor()), StudentMapper.mapToEntitySet(dto.getStudents()));
    }

    public static List<CourseDTO> mapToDTOList(List<HibernateCourse> courses) {
        if (courses == null) {
            return Collections.emptyList();
        }

        return courses.stream()
                .map(CourseMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    public static List<HibernateCourse> mapToEntityList(List<CourseDTO> courseDTOs) {
        if (courseDTOs == null) {
            return Collections.emptyList();
        }

        return courseDTOs.stream()
                .map(CourseMapper::mapToEntity)
                .collect(Collectors.toList());
    }


}
