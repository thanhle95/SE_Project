package edu.mum.mumsched.service.imp;

import edu.mum.mumsched.dao.FacultyDao;
import edu.mum.mumsched.domain.Faculty;
import edu.mum.mumsched.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyServiceImp implements FacultyService {
    @Autowired
    FacultyDao facultyDao;

    public void save(Faculty faculty){
        facultyDao.save(faculty);
    }

    @Override
    public List<Faculty> getFaculty() {
        return (List<Faculty>) facultyDao.findAll();
    }

    @Override
    public Faculty getFacultyByFacultyName(String facultyName) {
        return facultyDao.findFacultyByFacultyName(facultyName);
    }


    @Override
    public Faculty getFacultyByEmail(String email){
        return facultyDao.findFacultyByEmail(email);
    }

    @Override
    public Faculty getFacultyById(long facultyId){
        return facultyDao.findById(facultyId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid faculty Id:" + facultyId));
    }

    @Override
    public void deleteById(long facultyId){
        facultyDao.deleteById(facultyId);
    }

}
