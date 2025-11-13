package se.javapp.schoolsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.javapp.schoolsystem.model.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer>
{

}
