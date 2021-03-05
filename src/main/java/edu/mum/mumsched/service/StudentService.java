package edu.mum.mumsched.service;

import edu.mum.mumsched.domain.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {
    Student getStudentByEmail(String email);

    Student getStudentById(long studentId);

    void deleteById(long studentId);

    void save(Student student);

    List<Student> getStudent();
}
