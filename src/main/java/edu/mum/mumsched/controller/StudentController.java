package edu.mum.mumsched.controller;

import edu.mum.mumsched.domain.Student;
import edu.mum.mumsched.service.FacultyService;
import edu.mum.mumsched.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Controller
public class StudentController {
    @Autowired
    StudentService studentService;

    @Autowired
    FacultyService facultyService;

    @RequestMapping(value="/student/add", method= RequestMethod.GET)
    public String studentRegForm(@ModelAttribute("newStudent") Student student, Model model){

        model.addAttribute("newStudent", student);

        return "studentRegForm";
    }

    @RequestMapping(value="/student", method= RequestMethod.GET)
    public String showStudentList(Model model) {
        List<Student> students = studentService.getStudent();

        model.addAttribute("studentlist", students);

        return "studentList";
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
        return "studentUpdateForm";
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

}
