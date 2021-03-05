package edu.mum.mumsched.controller;


import edu.mum.mumsched.domain.Course;
import edu.mum.mumsched.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

public class CourseController {
    @Autowired
    CourseService courseService;

    @RequestMapping(value = "/course", method = RequestMethod.GET)
    public String courseRegForm(@ModelAttribute("newCourse")Course course, Model model){
        model.addAttribute("newCourse", course);
        return "addCourseForm";
    }

    @RequestMapping(value = {"/course/addnewcourse"}, method = RequestMethod.POST)
    public String registerNewCourse(@ModelAttribute("newCourse") Course course, Model model) {

        courseService.save(course);

        return "redirect:/course";
    }

}
