package edu.mum.mumsched.controller;

import edu.mum.mumsched.domain.Student;
import edu.mum.mumsched.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StudentController {
    @Autowired
    StudentService studentService;

    @RequestMapping(value="/", method= RequestMethod.GET)
    public String studentRegForm(@ModelAttribute("newStudent") Student student, Model model){
        model.addAttribute("newStudent", student);
        return "studentregform";
    }

    @RequestMapping(value = {"/addnewstudent"}, method = RequestMethod.POST)
    public String registerStudent(@ModelAttribute("newStudent") Student student, Model model){

        //STUDENT SAVED IN PERSISTENCE
        studentService.save(student);

        //GET STUDENT FROM PERSISTENCE
        model.addAttribute(studentService.getStudentByEmail(student.getEmail()));

        return "addsuccess";
    }
}
