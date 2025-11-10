package se.javapp.schoolsystem.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class StudentDTO {
    @NotBlank(message = "name cannot be empty")
    @Size(min = 2, message = "name must contain at least two or more characters")
    private final String name;
    @Positive(message = "age cannot be zero or negative")
    private final int age;
    private final String email;

    public StudentDTO(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }
}
