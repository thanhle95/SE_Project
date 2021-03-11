package edu.mum.mumsched.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long studentId;

    @NotEmpty
    private String studentFirstName;
    private String studentLastName;
    private String facultyName;
    private String email;

    @JoinColumn(name="facultyId",nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Faculty faculty;

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }


    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long id) {
        this.studentId = id;
    }

    public String getStudentFirstName() {
        return studentFirstName;
    }

    public void setStudentFirstName(String firstName) {
        this.studentFirstName = firstName;
    }

    public String getStudentLastName() {
        return studentLastName;
    }

    public void setStudentLastName(String lastName) {
        this.studentLastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }
}
