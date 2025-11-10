package se.javapp.schoolsystem.model.dto;

import java.time.LocalDate;

public class EnrollmentDTO {
    private int enrollmentId;
    private int studentId;
    private int courseId;
    LocalDate date;

    public EnrollmentDTO(int enrollmentId, int studentId, int courseId, LocalDate date) {
        this.enrollmentId = enrollmentId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.date = date;
    }

    public int getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(int enrollmentId) {
        this.enrollmentId = enrollmentId;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
