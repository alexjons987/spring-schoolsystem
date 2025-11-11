package se.javapp.schoolsystem.model.dto;

import se.javapp.schoolsystem.model.Student;

import java.util.List;

public class CourseRequestDTO {
    private String title;
    private String teacher;
    private int maxStudents;

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
