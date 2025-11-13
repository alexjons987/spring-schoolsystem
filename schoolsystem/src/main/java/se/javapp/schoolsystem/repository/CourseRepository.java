package se.javapp.schoolsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.javapp.schoolsystem.model.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {
}
