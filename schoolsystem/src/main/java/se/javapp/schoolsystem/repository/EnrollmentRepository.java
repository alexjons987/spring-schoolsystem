package se.javapp.schoolsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.javapp.schoolsystem.model.Enrollment;

import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {

    public Optional<Enrollment> findByStudentIdAndCourseId(int studentId, int courseId);

}
