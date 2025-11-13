package se.javapp.schoolsystem.model;

import jakarta.persistence.*;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String teacher;
    private int maxStudents;
    // TODO: add list of enrollments with @OneToMany?
    // private List<Integer> studentIds;

    public Course() {}

    public Course(String title, String teacher, int maxStudents) {
        this.title = title;
        this.teacher = teacher;
        this.maxStudents = maxStudents;
        //this.studentIds = studentIds;
    }

    public Course(int id, String title, String teacher, int maxStudents) {
        this.id = id;
        this.title = title;
        this.teacher = teacher;
        this.maxStudents = maxStudents;
       // this.studentIds = studentIds;
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

    // TODO: add getter/setter for enrollments
    // public List<Integer> getStudentIds() {
    //   return studentIds;
    // }
    //
    // public void addStudent(Integer id) {
    //   this.studentIds.add(id);
    // }

    // TODO: removeStudent
    // public void removeStudent()
}
