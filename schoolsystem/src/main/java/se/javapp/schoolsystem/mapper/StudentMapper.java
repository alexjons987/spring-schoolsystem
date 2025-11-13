package se.javapp.schoolsystem.mapper;

import org.springframework.stereotype.Component;
import se.javapp.schoolsystem.model.Student;
import se.javapp.schoolsystem.model.dto.StudentDTO;

@Component
public class StudentMapper {

    public StudentMapper() {
    }

    public StudentDTO toDTO(Student student) {
        if (student == null) return null;

        return new StudentDTO(student.getName(), student.getAge(), student.getEmail());
    }

    public Student toEntity(StudentDTO studentDTO) {
        if (studentDTO == null) return null;

        return new Student(studentDTO.getName(), studentDTO.getAge(), studentDTO.getEmail());
    }
}
