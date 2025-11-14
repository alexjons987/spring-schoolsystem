package se.javapp.schoolsystem.service;

import org.springframework.stereotype.Service;
import se.javapp.schoolsystem.exception.ResourceNotFoundException;
import se.javapp.schoolsystem.mapper.StudentMapper;
import se.javapp.schoolsystem.model.Student;
import se.javapp.schoolsystem.model.dto.StudentDTO;
import se.javapp.schoolsystem.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    public List<StudentDTO> getAllStudents() {
        List<Student> allStudents = studentRepository.findAll();

        if (!allStudents.isEmpty()) {
            return allStudents.stream()
                    .map(studentMapper::toDTO)
                    .toList();
        } else {
            throw new ResourceNotFoundException("No students were found in the repository");
        }
    }

    public StudentDTO getStudentById(int id) {
        return studentRepository.findById(id)
                .map(studentMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("No student with ID %d was found", id)));
    }

    public List<StudentDTO> filterStudents(String name, String letter, Integer minAge, Integer maxAge) {
        List<Student> allStudents = studentRepository.findAll();

        return allStudents.stream()
                .filter(s -> name == null || s.getName().toLowerCase().contains(name.toLowerCase().trim()))
                .filter(s -> letter == null || s.getName().startsWith(letter))
                .filter(s -> minAge == null || s.getAge() >= minAge)
                .filter(s -> maxAge == null || s.getAge() <= maxAge)
                .map(studentMapper::toDTO)
                .toList();
    }

    public StudentDTO createStudent(StudentDTO studentDTO) {
        Student student = studentRepository.save(
                new Student(studentDTO.getName(), studentDTO.getAge(), studentDTO.getEmail())
        );

        return studentMapper.toDTO(student);
    }

    public boolean deleteStudentById(int id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public StudentDTO updateStudentById(int id, StudentDTO newDetailsDTO) {
        Optional<Student> student = studentRepository.findById(id);

        if (student.isPresent()) {
            if (newDetailsDTO.getName() != null) {
                student.get().setName(newDetailsDTO.getName());
            }
            if (newDetailsDTO.getAge() != null) {
                student.get().setAge(newDetailsDTO.getAge());
            }
            if (newDetailsDTO.getEmail() != null) {
                student.get().setEmail(newDetailsDTO.getEmail());
            }

            studentRepository.save(student.get());
            return studentMapper.toDTO(student.get());
        }
        return null;
    }
}
