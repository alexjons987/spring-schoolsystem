package se.javapp.schoolsystem.service;

import org.springframework.stereotype.Service;
import se.javapp.schoolsystem.exception.StudentAlreadyEnrolledException;
import se.javapp.schoolsystem.model.Enrollment;
import se.javapp.schoolsystem.model.Student;
import se.javapp.schoolsystem.model.dto.EnrollmentDTO;
import se.javapp.schoolsystem.repository.EnrollmentRepository;
import se.javapp.schoolsystem.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository, StudentRepository studentRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
    }

    public List<EnrollmentDTO> getAllEnrollments() {
        return enrollmentRepository.getAllEnrollments().stream().map(this::toDTO).toList();
    }

    public Optional<EnrollmentDTO> createEnrollment(int studentId, int courseId) {
        Student student = studentRepository.getById(studentId);

        // TODO: Get course, error if not exists

        // TODO: Check max number of students on the course

        if (enrollmentRepository.getEnrollment(studentId, courseId).isPresent()) {
            throw new StudentAlreadyEnrolledException("Student is already enrolled is course");
        }

        return Optional.of(toDTO(enrollmentRepository.save(new Enrollment(studentId, courseId))));
    }

    private EnrollmentDTO toDTO(Enrollment enrollment) {
        if (enrollment == null)
            return null;

        return new EnrollmentDTO(enrollment.getEnrollmentId(), enrollment.getStudentId(), enrollment.getCourseId(), enrollment.getDate());
    }
}
