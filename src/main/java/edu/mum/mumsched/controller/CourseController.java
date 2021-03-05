package edu.mum.mumsched.controller;

import edu.mum.mumsched.domain.Block;
import edu.mum.mumsched.domain.Course;
import edu.mum.mumsched.service.BlockService;
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

    @Autowired
    BlockService blockService;

    @RequestMapping(value = "/course", method = RequestMethod.GET)
    public String courseRegForm(@ModelAttribute("newCourse")Course course, Model model){
        List<Course> courseList = new ArrayList<>();
        courseList.addAll(courseService.getAllCourse());
        List<String> blockNameList = new ArrayList<>();
        for(Block block: blockService.getAllBlock()){
            blockNameList.add(block.getBlockName());
        }
        model.addAttribute("blockNameList", blockNameList);
        model.addAttribute("courseList", courseList);
        model.addAttribute("newCourse", course);
        return "addCourseForm";
    }

    @RequestMapping(value = {"/course/addnewcourse"}, method = RequestMethod.POST)
    public String registerNewBlock(@ModelAttribute("newCourse") Course course, Model model) {
        Block block = blockService.getBlockByBlockID(course.getBlockId());
        block.addCourse(course);
        courseService.save(course);
        return "redirect:/course";
    }

}
