package edu.mum.mumsched.service.imp;

import edu.mum.mumsched.dao.StudentDao;
import edu.mum.mumsched.domain.Student;
import edu.mum.mumsched.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImp implements StudentService {
    @Autowired
    StudentDao studentDao;

    public void save(Student student){
        studentDao.save(student);
    }

    @Override
    public List<Student> getStudent() {
        return (List<Student>) studentDao.findAll();
    }

    @Override
    public Student getStudentByEmail(String email){
        return studentDao.findStudentByEmail(email);
    }

    @Override
    public Student getStudentById(long studentId){
        return studentDao.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + studentId));
    }

    @Override
    public void deleteById(long studentId){
        studentDao.deleteById(studentId);
    }

}
