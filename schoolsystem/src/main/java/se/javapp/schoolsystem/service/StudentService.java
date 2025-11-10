package se.javapp.schoolsystem.service;

import org.springframework.stereotype.Service;
import se.javapp.schoolsystem.model.Student;
import se.javapp.schoolsystem.model.dto.StudentDTO;

@Service
public class StudentService {

    private StudentDTO toDTO(Student student) {
        if (student == null) return null;

        return new StudentDTO(student.getName(), student.getAge(), student.getEmail());
    }

    private Student toEntity(StudentDTO studentDTO) {
        if (studentDTO == null) return null;

        return new Student(studentDTO.getName(), studentDTO.getAge(), studentDTO.getEmail());
    }
}
