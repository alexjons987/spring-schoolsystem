package se.javapp.schoolsystem.service;

import org.springframework.stereotype.Service;
import se.javapp.schoolsystem.exception.ResourceNotFoundException;
import se.javapp.schoolsystem.mapper.StudentMapper;
import se.javapp.schoolsystem.model.Course;
import se.javapp.schoolsystem.model.Enrollment;
import se.javapp.schoolsystem.model.dto.CourseRequestDTO;
import se.javapp.schoolsystem.model.dto.CourseResponseDTO;
import se.javapp.schoolsystem.model.dto.StudentDTO;
import se.javapp.schoolsystem.repository.CourseRepository;
import se.javapp.schoolsystem.repository.EnrollmentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final StudentMapper studentMapper;

    public CourseService(CourseRepository courseRepository,
                         EnrollmentRepository enrollmentRepository,
                         StudentMapper studentMapper) {
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.studentMapper = studentMapper;
    }

    public List<CourseResponseDTO> getAllCourses() {
        List<Course> courses = courseRepository.findAll();

        if (!courses.isEmpty()) {
            return courses.stream()
                    .map(this::toResponseDto)
                    .toList();
        } else {
            throw new ResourceNotFoundException("No courses were found");
        }
    }

    public CourseResponseDTO getCourseById(int id) {
        return courseRepository.findById(id)
                .map(this::toResponseDto)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("No course with ID %d was found", id)));
    }

    public List<StudentDTO> getStudentsByCourseId(int id) {
        return enrollmentRepository.findByCourseId(id).stream()
                .map(Enrollment::getStudent)
                .map(studentMapper::toDTO)
                .toList();
    }

    public Optional<CourseResponseDTO> addCourse(CourseRequestDTO dto) {
        Course course = toEntity(dto);
        return Optional.of(toResponseDto(courseRepository.save(course)));
    }

    public List<CourseResponseDTO> filterCourses(String title, String teacher, Integer maxStudentsAllowed) {
        List<Course> allCourses = courseRepository.findAll();

        return allCourses.stream()
                .filter(c -> title == null || c.getTitle().toLowerCase().contains(title.toLowerCase().trim()))
                .filter(c -> teacher == null || c.getTeacher().toLowerCase().contains(teacher.toLowerCase().trim()))
                .filter(c -> maxStudentsAllowed == null || c.getMaxStudents() <= maxStudentsAllowed)
                .map(this::toResponseDto)
                .toList();
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
