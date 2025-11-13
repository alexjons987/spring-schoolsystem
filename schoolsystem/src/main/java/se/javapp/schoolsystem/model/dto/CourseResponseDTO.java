package se.javapp.schoolsystem.model.dto;

import se.javapp.schoolsystem.model.Student;

import java.util.ArrayList;
import java.util.List;

public class CourseResponseDTO {
    private int id;
    private String title;
    private String teacher;
    private int maxStudents;
    //private List<Integer> studentIds;

    public CourseResponseDTO(int id, String title, String teacher, int maxStudents) {
        this.id = id;
        this.title = title;
        this.teacher = teacher;
        this.maxStudents = maxStudents;
        // TODO: enrollments?
        //this.studentIds = studentIds;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    // TODO: getter/setter for enrollments?
//    public List<Integer> getStudentIds() {
//        return studentIds;
//    }
}
