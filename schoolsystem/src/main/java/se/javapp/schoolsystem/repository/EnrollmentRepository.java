package se.javapp.schoolsystem.repository;

import org.springframework.stereotype.Repository;
import se.javapp.schoolsystem.model.Enrollment;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EnrollmentRepository {
    List<Enrollment> enrollments = new ArrayList<>();

    public List<Enrollment> getAllEnrollments() {
        return enrollments;
    }

    public Optional<Enrollment> getEnrollment(int studentId, int courseId) {
        return enrollments.stream()
                .filter(e -> e.getStudentId() == studentId)
                .filter(e -> e.getCourseId() == courseId)
                .findFirst();
    }

    public Enrollment save(Enrollment enrollment) {
        int nextId = enrollments.stream()
                .mapToInt(Enrollment::getEnrollmentId)
                .max()
                .orElse(0) + 1;
        enrollment.setEnrollmentId(nextId);
        enrollments.add(enrollment);
        return enrollment;
    }
}
