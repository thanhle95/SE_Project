package edu.mum.mumsched.dao;

import edu.mum.mumsched.domain.Course;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseDao extends CrudRepository<Course, Long> {
    @Query("select c from Course c where c.courseId= :courseID")
    public Course findCourseByCourseID(@Param("courseID") long courseID);

    @Query("select c from Course c where c.courseName= :courseName")
    public Course findCourseByCourseName(@Param("courseName") String courseName);

    @Query("select c from Course c")
    public List<Course> getAllCourse();
}
