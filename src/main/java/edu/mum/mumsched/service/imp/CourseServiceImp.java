package edu.mum.mumsched.service.imp;

import edu.mum.mumsched.dao.CourseDao;
import edu.mum.mumsched.domain.Course;
import edu.mum.mumsched.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImp implements CourseService {
    @Autowired
    CourseDao courseDao;

    @Override
    public void save(Course course) {
        courseDao.save(course);
    }

    @Override
    public Course getCourseByCourseName(String courseName){
        return courseDao.findCourseByCourseName(courseName);
    }

    @Override
    public Course getCourseByCourseID(long courseID) {
        return courseDao.findCourseByCourseID(courseID);
    }

    @Override
    public List<Course> getAllCourse() {
        return courseDao.getAllCourse();
    }
}