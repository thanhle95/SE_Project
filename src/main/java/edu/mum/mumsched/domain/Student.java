package edu.mum.mumsched.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long studentId;

    @NotEmpty
    private String studentFirstName;
    private String studentLastName;
    private String email;

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
}
