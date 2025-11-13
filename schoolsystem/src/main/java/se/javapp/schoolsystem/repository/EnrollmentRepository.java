package se.javapp.schoolsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.javapp.schoolsystem.model.Enrollment;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {
    Optional<Enrollment> findByStudentIdAndCourseId(int studentId, int courseId);

    List<Enrollment> findByCourseId(int courseId);
}
