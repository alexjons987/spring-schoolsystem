package se.javapp.schoolsystem.service;

import org.springframework.stereotype.Service;
import se.javapp.schoolsystem.exception.MaxNumberOfStudentsInCourseException;
import se.javapp.schoolsystem.exception.ResourceNotFoundException;
import se.javapp.schoolsystem.exception.StudentAlreadyEnrolledException;
import se.javapp.schoolsystem.model.Course;
import se.javapp.schoolsystem.model.Enrollment;
import se.javapp.schoolsystem.model.Student;
import se.javapp.schoolsystem.model.dto.CourseResponseDTO;
import se.javapp.schoolsystem.model.dto.EnrollmentDTO;
import se.javapp.schoolsystem.model.dto.StudentDTO;
import se.javapp.schoolsystem.repository.CourseRepository;
import se.javapp.schoolsystem.repository.EnrollmentRepository;
import se.javapp.schoolsystem.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository, StudentRepository studentRepository, CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public List<EnrollmentDTO> getAllEnrollments() {
        List<Enrollment> enrollments = enrollmentRepository.findAll();

        if (enrollments.isEmpty())
            throw new ResourceNotFoundException("No enrollments were found in the repository");

        return enrollments.stream()
                .map(this::toDTO)
                .toList();
    }

    public EnrollmentDTO getEnrollmentById(int enrollmentId) {
        Optional<Enrollment> enrollment = enrollmentRepository.findById(enrollmentId);

        if (enrollment.isEmpty())
            throw new ResourceNotFoundException(String.format("No enrollment with ID %d was found", enrollmentId));

        return toDTO(enrollment.get());
    }

    public Optional<EnrollmentDTO> createEnrollment(int studentId, int courseId) {
        Optional<Student> student = studentRepository.findById(studentId);
        if (student.isEmpty())
            throw new ResourceNotFoundException(String.format("No student with ID %d was found", studentId));

        Optional<Course> course = courseRepository.findById(courseId);
        if (course.isEmpty())
            throw new ResourceNotFoundException(String.format("No course with ID %d was found", courseId));

        if (enrollmentRepository.findByStudentIdAndCourseId(studentId, courseId).isPresent())
            throw new StudentAlreadyEnrolledException("Student is already enrolled in course");

        if (course.get().getEnrollments().size() >= course.get().getMaxStudents())
            throw new MaxNumberOfStudentsInCourseException(String.format("Course is full, max number of students is %d", course.get().getMaxStudents()));

        return Optional.of(toDTO(enrollmentRepository.save(new Enrollment(student.get(), course.get()))));
    }

    public boolean deleteEnrollmentById(int id) {
        if (enrollmentRepository.existsById(id)) {
            enrollmentRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    private EnrollmentDTO toDTO(Enrollment enrollment) {
        if (enrollment == null)
            return null;

        StudentDTO studentDTO = new StudentDTO(enrollment.getStudent().getName(), enrollment.getStudent().getAge(), enrollment.getStudent().getEmail());
        CourseResponseDTO courseDTO = new CourseResponseDTO(enrollment.getCourse().getId(), enrollment.getCourse().getTitle(), enrollment.getCourse().getTeacher(), enrollment.getCourse().getMaxStudents());

        return new EnrollmentDTO(enrollment.getEnrollmentId(), studentDTO, courseDTO, enrollment.getDate());
    }
}
