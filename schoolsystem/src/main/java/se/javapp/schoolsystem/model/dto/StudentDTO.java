package se.javapp.schoolsystem.model.dto;

public class StudentDTO {
    private final String name;
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
