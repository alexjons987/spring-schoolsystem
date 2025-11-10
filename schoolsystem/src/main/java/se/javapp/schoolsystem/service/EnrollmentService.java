package se.javapp.schoolsystem.service;

import org.springframework.stereotype.Service;
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
        return enrollmentRepository.getAllEnrollments().stream().map(this::toDTO).toList();
    }

    public Optional<EnrollmentDTO> createEnrollment(int studentId, int courseId) {
        // TODO: Get student, error if not exists

        // TODO: Get course, error if not exists

        // TODO: Check max number of students on the course

        // Check if student is already enrolled on the course
        if (enrollmentRepository.getEnrollment(studentId, courseId).isPresent()) {
            // TODO: Throw Exception instead ???
            return Optional.empty();
        }

        return Optional.of(toDTO(enrollmentRepository.save(new Enrollment(studentId, courseId))));
    }

    private EnrollmentDTO toDTO(Enrollment enrollment) {
        if (enrollment == null)
            return null;

        return new EnrollmentDTO(enrollment.getEnrollmentId(), enrollment.getStudentId(), enrollment.getCourseId(), enrollment.getDate());
    }
}
