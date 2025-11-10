package se.javapp.schoolsystem.service;

import org.springframework.stereotype.Service;
import se.javapp.schoolsystem.model.Student;
import se.javapp.schoolsystem.model.dto.StudentDTO;
import se.javapp.schoolsystem.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<StudentDTO> getAllStudents() {
        List<StudentDTO> studentDTOs = new ArrayList<>();

        for (Student student : studentRepository.getAll()) {
            studentDTOs.add(this.toDTO(student));
        }

        return studentDTOs;
    }

    public Optional<StudentDTO> createStudent(StudentDTO studentDTO) {
        Student student = studentRepository.save(this.toEntity(studentDTO));

        return Optional.of(this.toDTO(student));
    }

    private StudentDTO toDTO(Student student) {
        if (student == null) return null;

        return new StudentDTO(student.getName(), student.getAge(), student.getEmail());
    }

    private Student toEntity(StudentDTO studentDTO) {
        if (studentDTO == null) return null;

        return new Student(studentDTO.getName(), studentDTO.getAge(), studentDTO.getEmail());
    }
}
