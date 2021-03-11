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

}
