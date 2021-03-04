package edu.mum.mumsched.service;

import edu.mum.mumsched.domain.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {
    Student getStudentByEmail(String email);

    Student getStudentById(Long studentId);

    void deleteById(Long studentId);

    void save(Student student);

    List<Student> getStudent();
}
