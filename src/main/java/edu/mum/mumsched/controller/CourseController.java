package edu.mum.mumsched.controller;


import edu.mum.mumsched.domain.Course;
import edu.mum.mumsched.domain.Student;
import edu.mum.mumsched.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CourseController {
    @Autowired
    CourseService courseService;

    // Display List Course
    @RequestMapping(value="/course", method= RequestMethod.GET)
    public String courseList(Model model) {
        List<Course> courseList = courseService.getAllCourse();
        model.addAttribute("courseList", courseList);
        return "courseList";
    }

    // Update Course Detail
    @RequestMapping("/course/update/{id}")
    public String showUpdateForm(@PathVariable("id") long id, @Valid Course course, Model model) {
        course.setCourseId(id);
        courseService.save(course);
        model.addAttribute("course", courseService.getAllCourse());
        return "redirect:/course";

    }

    @RequestMapping("/course/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Course course = courseService.getCourseByCourseID(id);
        model.addAttribute("course", course);
        return "courseUpdateForm";
    }

    // Add more course
    @RequestMapping(value = "/course/add", method = RequestMethod.GET)
    public String courseRegForm(@ModelAttribute("newCourse")Course course, Model model){
        model.addAttribute("newCourse", course);
        return "addCourseForm";
    }

    @RequestMapping(value = {"/course/addnewcourse"}, method = RequestMethod.POST)
    public String registerNewCourse(@ModelAttribute("newCourse") Course course, Model model) {
        courseService.save(course);
        return "redirect:/course";
    }

    // Delete Course
    @RequestMapping(value = "/course/delete/{id}", method = RequestMethod.GET)
    public String deleteStudent(@PathVariable("id") Long courseId, Model model) {
        courseService.deleteById(courseId);
        return "redirect:/course";
    }
}
