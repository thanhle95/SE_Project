package edu.mum.mumsched.service;

import edu.mum.mumsched.domain.Student;
import org.springframework.stereotype.Service;

@Service
public interface StudentService {
    Student getStudentByEmail(String email);

    Student getStudentById(Long studentId);

    void save(Student student);
}
