package edu.mum.mumsched.service;

import edu.mum.mumsched.domain.Student;

public interface StudentService {
    Student getStudentByEmail(String email);

    Student getStudentById(Long studentId);

    void save(Student student);
}
