package se.javapp.schoolsystem.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
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

    public StudentDTO getStudentById(int id) {
        return this.toDTO(studentRepository.getById(id));
    }

    public StudentDTO createStudent(StudentDTO studentDTO) {
        Student student = studentRepository.save(this.toEntity(studentDTO));

        return this.toDTO(student);
    }

    public boolean deleteStudentById(int id) {
        return studentRepository.deleteById(id);
    }

    public StudentDTO updateStudentById(int id, StudentDTO newDetailsDTO) {
        Optional<Student> existing = Optional.of(studentRepository.getById(id));

        if (existing.isPresent()) {
            Student student = existing.get();

            if (newDetailsDTO.getName() != null) {
                student.setName(newDetailsDTO.getName());
            }
            if (newDetailsDTO.getAge() != null) {
                student.setAge(newDetailsDTO.getAge());
            }
            if (newDetailsDTO.getEmail() != null) {
                student.setEmail(newDetailsDTO.getEmail());
            }

            studentRepository.save(student);
            return this.toDTO(student);
        }
        return null;
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
