package se.javapp.schoolsystem.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import se.javapp.schoolsystem.model.dto.StudentDTO;
import se.javapp.schoolsystem.service.StudentService;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable int id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<StudentDTO> createStudent(@Valid @RequestBody StudentDTO studentDTO) {
        return ResponseEntity.ok(studentService.createStudent(studentDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable int id) {
        boolean removedStudent = studentService.deleteStudentById(id);
        return removedStudent ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable int id, @RequestBody StudentDTO newDetailsDTO) {
        return ResponseEntity.ok(studentService.updateStudentById(id, newDetailsDTO));
    }
}
