package edu.mum.mumsched.controller;

import edu.mum.mumsched.domain.*;
import edu.mum.mumsched.service.FacultyService;
import edu.mum.mumsched.service.SessionService;
import edu.mum.mumsched.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class StudentController {
    @Autowired
    StudentService studentService;

    @Autowired
    FacultyService facultyService;

    @Autowired
    SessionService sessionService;

    @RequestMapping(value="/student/add", method= RequestMethod.GET)
    public String studentRegForm(@ModelAttribute("newStudent") Student student, Model model){

        model.addAttribute("newStudent", student);

        return "student/studentRegForm";
    }

    @RequestMapping(value="/student", method= RequestMethod.GET)
    public String showStudentList(Model model) {
        List<Student> students = studentService.getStudent();

        model.addAttribute("studentlist", students);

        return "student/studentList";
    }

    @RequestMapping("/student/update/{id}")
    public String updateStudent(@PathVariable("id") long id, @Valid Student student, Model model) {


        student.setStudentId(id);

        studentService.save(student);

        model.addAttribute("students", studentService.getStudent());
        return "redirect:/student";

    }

    @RequestMapping("/student/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Student student = studentService.getStudentById(id);

        model.addAttribute("student", student);
        return "student/studentUpdateForm";
    }

    @RequestMapping(value = {"/student/addnewstudent"}, method = RequestMethod.POST)
    public String registerStudent(@ModelAttribute("newStudent") Student student){

        //STUDENT SAVED IN PERSISTENCE
        studentService.save(student);

        return "redirect:/student";
    }

    @RequestMapping(value = "/student/delete/{id}", method = RequestMethod.GET)
    public String deleteStudent(@PathVariable("id") Long studentId) {
        studentService.deleteById(studentId);
        return "redirect:/student";
    }



    @RequestMapping(value="/student/homepage", method= RequestMethod.GET)
    public String studentHomePage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth.isAuthenticated()) {
            MiuUserDetails miuUserDetails = (MiuUserDetails) auth.getPrincipal();
            Student student = studentService.getStudentByUserEmail(miuUserDetails.getUsername());
            model.addAttribute("student", student);
        }
        return "student/studentHomeForm";
    }

    @RequestMapping(value="/student/profile", method= RequestMethod.GET)
    public String studentProfilePage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth.isAuthenticated()) {
            MiuUserDetails miuUserDetails = (MiuUserDetails) auth.getPrincipal();
            Student student = studentService.getStudentByUserEmail(miuUserDetails.getUsername());
            model.addAttribute("student", student);
        }
        return "student/studentProfileForm";
    }

    @RequestMapping(value="/student/schedule", method= RequestMethod.GET)
    public String studentSchedulePage(Model model) {

        return "";
    }

    @RequestMapping(value = "/student/register_course_schedule", method = RequestMethod.GET)
    public String registerCourseSchedule(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth.isAuthenticated()) {
            MiuUserDetails miuUserDetails = (MiuUserDetails) auth.getPrincipal();
            Student student = studentService.getStudentByUserEmail(miuUserDetails.getUsername());

            Schedule schedule = null;
            for (Schedule sc : student.getEntry().getScheduleList()) {
                schedule = sc;
            }

            model.addAttribute("schedule", schedule);
            model.addAttribute("student", student);
        }
        return "student/studentScheduleForm";
    }

    @RequestMapping(value = "/student/register_course/{id}", method = RequestMethod.GET)
    public String registerCourse(@PathVariable("id") Long sessionId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth.isAuthenticated()){
            MiuUserDetails miuUserDetails = (MiuUserDetails)auth.getPrincipal();
            Student student = studentService.getStudentByUserEmail(miuUserDetails.getUsername());
            Session session = sessionService.getSessionBySessionId(sessionId);
            if(session.getSessionEnrolled() < session.getSessionCapacity()) {
                session.addStudents(student);
                student.addSession(session);
                studentService.save(student);
                sessionService.save(session);
            }
        }
        return "redirect:/student/register_course_schedule";
    }
}
