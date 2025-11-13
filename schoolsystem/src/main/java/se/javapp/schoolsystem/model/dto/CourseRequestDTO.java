package se.javapp.schoolsystem.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class CourseRequestDTO {
    @NotBlank(message = "Title can not be empty")
    private String title;
    @NotBlank(message = "Teacher can not be empty")
    private String teacher;
    @Positive(message = "Max number must be positive")
    private Integer maxStudents;

    public CourseRequestDTO() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public int getMaxStudents() {
        return maxStudents;
    }

    public void setMaxStudents(int maxStudents) {
        this.maxStudents = maxStudents;
    }
}
