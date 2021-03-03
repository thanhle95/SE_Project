package edu.mum.mumsched.service.imp;

import edu.mum.mumsched.dao.StudentDao;
import edu.mum.mumsched.domain.Student;
import edu.mum.mumsched.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImp implements StudentService {
    @Autowired
    StudentDao studentDao;

    public void save(Student student){
        studentDao.save(student);
    }

    @Override
    public Student getStudentByEmail(String email){
        return studentDao.findStudentByEmail(email);
    }

    @Override
    public Student getStudentById(Long studentId){
        return studentDao.findStudentById(studentId);
    }
}
