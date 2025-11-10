package se.javapp.schoolsystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.javapp.schoolsystem.model.dto.EnrollmentDTO;
import se.javapp.schoolsystem.service.EnrollmentService;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @GetMapping
    public ResponseEntity<List<EnrollmentDTO>> getAllEnrollments() {
        return ResponseEntity.ok(enrollmentService.getAllEnrollments());
    }

    @PostMapping("/create")
    public ResponseEntity<EnrollmentDTO> createEnrollment(@RequestParam int studentId, @RequestParam int courseId) {
        return enrollmentService.createEnrollment(studentId, courseId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }
}
