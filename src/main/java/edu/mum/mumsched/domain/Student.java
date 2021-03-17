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
    private String email;
    private long userId;

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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
