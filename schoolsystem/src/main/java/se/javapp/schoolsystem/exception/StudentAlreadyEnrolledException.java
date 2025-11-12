package se.javapp.schoolsystem.exception;

public class StudentAlreadyEnrolledException extends RuntimeException {
    public StudentAlreadyEnrolledException(String message) {
        super(message);
    }
}
