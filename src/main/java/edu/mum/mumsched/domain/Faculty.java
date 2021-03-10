package edu.mum.mumsched.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long facultyId;

    @NotEmpty
    private String facultyName;
    private String email;

    @OneToMany(mappedBy = "faculty")
    private Set<Student> studentList = new HashSet<>();

    public void addStudent(Student student) {
        studentList.add(student);
        student.setFaculty(this);
    }

    public void removeStudent(Student student){
        studentList.remove(student);
        student.setFaculty(null);
    }

    public Set<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(Set<Student> studentList) {
        this.studentList = studentList;
    }

    public long getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(long facultyId) {
        this.facultyId = facultyId;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getNumOfStudent(){
        return this.studentList.size();
    }
}
