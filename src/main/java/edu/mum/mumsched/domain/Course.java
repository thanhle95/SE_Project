package edu.mum.mumsched.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long courseId;
    @NotEmpty
    private String courseCode;
    private String courseName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private Set<Course> prerequisitesCourse = new HashSet<>();

    public void addPrerequisitesCourse(Course course) {
        prerequisitesCourse.add(course);
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Set<Course> getPrerequisitesCourse() {
        return prerequisitesCourse;
    }

    public void setPrerequisitesCourse(Set<Course> prerequisitesCourse) {
        this.prerequisitesCourse = prerequisitesCourse;
    }
}
