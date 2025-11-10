package se.javapp.schoolsystem.model;

import java.time.LocalDate;

public class Enrollment {
    private int studentId;
    private int courseId;
    LocalDate date;

    public Enrollment(int studentId, int courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.date = LocalDate.now();
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}
