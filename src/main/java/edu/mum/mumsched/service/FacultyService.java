package edu.mum.mumsched.service;

import edu.mum.mumsched.domain.Faculty;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FacultyService {
    Faculty getFacultyByEmail(String email);

    Faculty getFacultyById(long facultyId);

    void deleteById(long facultyId);

    void save(Faculty faculty);

    public Faculty getFacultyByFacultyName(String facultyName);

    List<Faculty> getFaculty();
}
