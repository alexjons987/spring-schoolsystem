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

    @GetMapping("/name/{input}")
    public ResponseEntity<List<StudentDTO>> getStudentsByName(@PathVariable String input) {
        return ResponseEntity.ok(studentService.getStudentsByNamePartial(input));
    }

    @GetMapping("/letter/{input}")
    public ResponseEntity<List<StudentDTO>> getStudentsLetter(@PathVariable String input) {
        return ResponseEntity.ok(studentService.getStudentsByFirstLetter(input));
    }

    @GetMapping("/belowAge/{input}")
    public ResponseEntity<List<StudentDTO>> getStudentsBelowAge(@PathVariable int input) {
        return ResponseEntity.ok(studentService.getStudentsBelowAge(input));
    }

    @GetMapping("/aboveAge/{input}")
    public ResponseEntity<List<StudentDTO>> getStudentsAboveAge(@PathVariable int input) {
        return ResponseEntity.ok(studentService.getStudentsAboveAge(input));
    }

    @GetMapping("/betweenAge/{input}/{inputTwo}")
    public ResponseEntity<List<StudentDTO>> getStudentsByAgeBetween(@PathVariable int input, @PathVariable int inputTwo) {
        return ResponseEntity.ok(studentService.getStudentsByAgeBetween(input,inputTwo));
    }

    @PostMapping("/create")
    public ResponseEntity<StudentDTO> createStudent(@Valid @RequestBody StudentDTO studentDTO) {
        return ResponseEntity.ok(studentService.createStudent(studentDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable int id) {
        return studentService.deleteStudentById(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable int id, @RequestBody StudentDTO newDetailsDTO) {
        return ResponseEntity.ok(studentService.updateStudentById(id, newDetailsDTO));
    }
}
