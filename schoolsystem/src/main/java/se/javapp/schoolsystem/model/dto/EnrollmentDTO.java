package se.javapp.schoolsystem.model.dto;

import java.time.LocalDate;

public class EnrollmentDTO {
    private int enrollmentId;
    private StudentDTO student;
    private int courseId;
    LocalDate date;

    public EnrollmentDTO(int enrollmentId, StudentDTO student, int courseId, LocalDate date) {
        this.enrollmentId = enrollmentId;
        this.student = student;
        this.courseId = courseId;
        this.date = date;
    }

    public int getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(int enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public StudentDTO getStudent() {
        return student;
    }

    public void setStudent(StudentDTO student) {
        this.student = student;
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
