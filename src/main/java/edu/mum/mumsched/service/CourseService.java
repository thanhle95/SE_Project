package edu.mum.mumsched.service;

import edu.mum.mumsched.domain.Course;

import java.util.List;

public interface CourseService {
    public void save(Course course);

    public Course getCourseByCourseID(long courseID);

    public Course getCourseByCourseName(String courseName);

    public List<Course> getAllCourse();
}
