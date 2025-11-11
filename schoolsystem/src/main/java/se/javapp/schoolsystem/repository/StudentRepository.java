package se.javapp.schoolsystem.repository;

import org.springframework.stereotype.Repository;
import se.javapp.schoolsystem.exception.ResourceNotFoundException;
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
        if (students.isEmpty()) {
            throw new ResourceNotFoundException("No students were found in the repository");
        }

        return students;
    }

    public Student getById(int id) {
        return students.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Unable to find student with ID %d", id)
                ));
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

    public boolean deleteById(int id) {
        return students.removeIf(s -> s.getId() == id);
    }
}
