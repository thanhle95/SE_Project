package edu.mum.mumsched.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long sessionId;

    @NotEmpty
    private LocalDate startDate;
    private LocalDate endDate;
    private long facultyId;

    @JoinColumn(name="blockId",nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Block block;

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "courseId")
    private Course course;

    @ManyToMany(mappedBy = "sessions")
    private Set<Student> students = new HashSet<>();

    private int sessionCapacity;
    private int sessionEnrolled;
    private String sessionClassRoom;

    public long getSessionId() {
        return sessionId;
    }

    public void setSessionId(long sessionId) {
        this.sessionId = sessionId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public long getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(long facultyId) {
        this.facultyId = facultyId;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public int getSessionCapacity() {
        return sessionCapacity;
    }

    public void setSessionCapacity(int capacity) {
        this.sessionCapacity = capacity;
    }

    public int getSessionEnrolled() {
        return sessionEnrolled;
    }

    public void setSessionEnrolled(int sessionEnrolled) {
        this.sessionEnrolled = sessionEnrolled;
    }

    public void addStudents(Student student){
        this.students.add(student);
        this.sessionEnrolled++;
    }

    public void removeStudent(Student student) {
        if(this.students.remove(student)){
            this.sessionEnrolled--;
        }
    }

    public String getSessionClassRoom() {
        return sessionClassRoom;
    }

    public void setSessionClassRoom(String sessionClassRoom) {
        this.sessionClassRoom = sessionClassRoom;
    }

    public boolean isStudentRegistered(long studentID) {
        Optional<Student> foundStudent = students.stream().filter(x -> x.getStudentId() == studentID ).findAny();
        return foundStudent.isPresent();
    }
}
