package se.javapp.schoolsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.javapp.schoolsystem.model.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer>
{
    List<Student> findByAgeGreaterThan(int age);
    List<Student> findByAgeLessThan(int age);
    List<Student> findByAgeBetween(int age, int age2);
    List<Student> findByNameStartsWith(String name);
    List<Student> findByNameContains(String name);
}
