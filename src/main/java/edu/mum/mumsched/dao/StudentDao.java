package edu.mum.mumsched.dao;

import edu.mum.mumsched.domain.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentDao extends CrudRepository<Student, Long> {
    @Query("select s from Student s where s.studentId= :id")
    public Student findStudentById(@Param("id") Long studentId);

    @Query("select s from Student s where s.user.UserId= :id")
    public Student findStudentByUserId(@Param("id") Long userId);

    @Query("select s from Student s where s.user.email= :email")
    public Student findStudentByUserEmail(@Param("email") String email);

    @Query("delete from Student s where s.studentId= :id")
    public void removeById(@Param("id") Long studentId);

}
