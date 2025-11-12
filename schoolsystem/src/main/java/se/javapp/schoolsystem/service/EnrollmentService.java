package se.javapp.schoolsystem.service;

import org.springframework.stereotype.Service;
import se.javapp.schoolsystem.exception.ResourceNotFoundException;
import se.javapp.schoolsystem.exception.StudentAlreadyEnrolledException;
import se.javapp.schoolsystem.model.Enrollment;
import se.javapp.schoolsystem.model.dto.EnrollmentDTO;
import se.javapp.schoolsystem.repository.EnrollmentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
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
            throw new ResourceNotFoundException("No enrollment with id " + enrollmentId +" was found in the repository");

        return toDTO(enrollment.get());
    }

    public Optional<EnrollmentDTO> createEnrollment(int studentId, int courseId) {
        // TODO: Get student, error if not exists

        // TODO: Get course, error if not exists

        // TODO: Check max number of students on the course

        if (enrollmentRepository.findByStudentIdAndCourseId(studentId, courseId).isPresent()) {
            throw new StudentAlreadyEnrolledException("Student is already enrolled in course");
        }

        return Optional.of(toDTO(enrollmentRepository.save(new Enrollment(studentId, courseId))));
    }

    private EnrollmentDTO toDTO(Enrollment enrollment) {
        if (enrollment == null)
            return null;

        return new EnrollmentDTO(enrollment.getEnrollmentId(), enrollment.getStudentId(), enrollment.getCourseId(), enrollment.getDate());
    }
}
