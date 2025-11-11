package se.javapp.schoolsystem.repository;

import org.springframework.stereotype.Repository;
import se.javapp.schoolsystem.model.Course;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CourseRepository {
    private final List<Course> courses = new ArrayList<>();


    public Course save(Course course) {
        int nextInt = courses.stream()
                .mapToInt(c -> c.getId())
                .max()
                .orElse(0) +1;

        course.setId(nextInt);
        courses.add(course);

        return course;
    }
}
