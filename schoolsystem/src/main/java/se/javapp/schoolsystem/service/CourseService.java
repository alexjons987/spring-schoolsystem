package se.javapp.schoolsystem.service;

import org.springframework.stereotype.Service;
import se.javapp.schoolsystem.model.Course;
import se.javapp.schoolsystem.model.dto.CourseRequestDTO;
import se.javapp.schoolsystem.model.dto.CourseResponseDTO;
import se.javapp.schoolsystem.repository.CourseRepository;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepository repository;

    public CourseService(CourseRepository repository) {
        this.repository = repository;
    }

    public Optional<CourseResponseDTO> addCourse(CourseRequestDTO dto) {
        Course course = toEntity(dto);
        return Optional.of(toResponseDto(repository.save(course)));
    }

    public CourseResponseDTO toResponseDto(Course course) {
        return new CourseResponseDTO(
                course.getId(),
                course.getTitle(),
                course.getTeacher(),
                course.getMaxStudents()
        );
    }

    public Course toEntity(CourseRequestDTO dto) {
        return new Course(
                dto.getTitle(),
                dto.getTeacher(),
                dto.getMaxStudents()
        );
    }
}
