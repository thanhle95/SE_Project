package edu.mum.mumsched.dto;

import edu.mum.mumsched.domain.Session;

public class RabbitmqCourseDTO {
    private long courseId;
    private String courseCode;
    private String courseName;
    private String courseAbbrName;
    private String preCourseId;
    private long courseCapacity;
    private Session session;

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

    public String getCourseAbbrName() {
        return courseAbbrName;
    }

    public void setCourseAbbrName(String courseAbbrName) {
        this.courseAbbrName = courseAbbrName;
    }

    public String getPreCourseId() {
        return preCourseId;
    }

    public void setPreCourseId(String preCourseId) {
        this.preCourseId = preCourseId;
    }

    public long getCourseCapacity() {
        return courseCapacity;
    }

    public void setCourseCapacity(long courseCapacity) {
        this.courseCapacity = courseCapacity;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
