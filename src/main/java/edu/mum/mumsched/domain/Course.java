package edu.mum.mumsched.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
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
    private String courseAbbrName;

    @Lob
    @Column( length = 100000 )
    private String courseDescription;
    private String preCourseId;
    private long courseCapacity;

    @OneToOne(mappedBy = "course")
    private Session session;

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
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

    public String getPreCourseId() {
        return preCourseId;
    }

    public void setPreCourseId(String preCourseId) {
        this.preCourseId = preCourseId;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public String getCourseAbbrName() {
        return courseAbbrName;
    }

    public void setCourseAbbrName(String courseAbbrName) {
        this.courseAbbrName = courseAbbrName;
    }

    public long getCourseCapacity() {
        return courseCapacity;
    }

    public void setCourseCapacity(long courseCapacity) {
        this.courseCapacity = courseCapacity;
    }
}
