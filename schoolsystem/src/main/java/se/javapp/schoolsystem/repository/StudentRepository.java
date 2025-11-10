package se.javapp.schoolsystem.repository;

import org.springframework.stereotype.Repository;
import se.javapp.schoolsystem.model.Student;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepository {
    private final List<Student> students = new ArrayList<>();

    public StudentRepository() {
        students.add(new Student(1, "Alice", 25, "alice@email.se"));
        students.add(new Student(2, "Bob", 26, "bob@email.se"));
        students.add(new Student(3, "Carl", 27, "carl@email.se"));
        students.add(new Student(4, "Dede", 28, "dede@email.se"));
    }

    public List<Student> getAll() {
        return students;
    }

    public Student save(Student student) {
        int nextId = students.stream()
                .mapToInt(Student::getId)
                .max()
                .orElse(0) + 1;
        student.setId(nextId);
        students.add(student);
        return student;
    }
}
