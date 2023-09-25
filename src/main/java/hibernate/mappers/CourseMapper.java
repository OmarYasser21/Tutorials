package hibernate.mappers;

import hibernate.dtos.CourseDTO;
import hibernate.dtos.InstructorDTO;
import hibernate.entities.HibernateCourse;
import hibernate.entities.HibernateInstructor;

public class CourseMapper {
    public static CourseDTO mapToDTO(HibernateCourse course) {
        CourseDTO dto = new CourseDTO();
        dto.setId(course.getId());
        dto.setName(course.getName());
        dto.setStartDate(course.getStartDate());
        dto.setEndDate(course.getEndDate());
        dto.setLevel(course.getLevel());
        dto.setStarted(course.isStarted());
        dto.setInstructor(course.getInstructor());
        dto.setStudents(course.getStudents());
        return dto;
    }

    public static HibernateCourse mapToEntity(CourseDTO dto) {
        if (dto == null) {
            return null;
        }

        HibernateCourse entity = new HibernateCourse();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());
        entity.setLevel(dto.getLevel());
        entity.setStarted(dto.isStarted());
        entity.setInstructor(dto.getInstructor());
        entity.setStudents(dto.getStudents());
        return entity;
    }
}
