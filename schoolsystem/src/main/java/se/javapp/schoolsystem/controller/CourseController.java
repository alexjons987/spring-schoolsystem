package se.javapp.schoolsystem.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.javapp.schoolsystem.model.dto.CourseRequestDTO;
import se.javapp.schoolsystem.model.dto.CourseResponseDTO;
import se.javapp.schoolsystem.service.CourseService;

import java.util.Optional;

@RestController
@RequestMapping("/api/course")
public class CourseController {
    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    @PostMapping("/new-course")
    public ResponseEntity<CourseResponseDTO> addCourse(@Valid @RequestBody CourseRequestDTO dto) {
       return  service.addCourse(dto)
               .map(ResponseEntity::ok)
               .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }
}
