package se.javapp.schoolsystem.model.dto;

import java.time.LocalDate;

public class EnrollmentDTO {
    private int enrollmentId;
    private StudentDTO student;
    private CourseResponseDTO course;
    LocalDate date;

    public EnrollmentDTO(int enrollmentId, StudentDTO student, CourseResponseDTO course, LocalDate date) {
        this.enrollmentId = enrollmentId;
        this.student = student;
        this.course = course;
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

    public CourseResponseDTO getCourse() {
        return course;
    }

    public void setCourse(CourseResponseDTO course) {
        this.course = course;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
