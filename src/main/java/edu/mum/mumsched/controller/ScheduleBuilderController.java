package edu.mum.mumsched.controller;


import edu.mum.mumsched.domain.Block;
import edu.mum.mumsched.domain.Course;
import edu.mum.mumsched.domain.Entry;
import edu.mum.mumsched.service.BlockService;
import edu.mum.mumsched.service.CourseService;
import edu.mum.mumsched.service.EntryService;
import edu.mum.mumsched.service.ScheduleBuilderService;
import edu.mum.mumsched.service.imp.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class ScheduleBuilderController {

    @Autowired
    ScheduleBuilderService scheduleBuilderService;

    @Autowired
    EntryService entryService;

    @Autowired
    CourseService courseService;

    @Autowired
    BlockService blockService;

    @Autowired
    RestService restService;

    @RequestMapping(value = "/schedule/builder", method = RequestMethod.GET)
    public String scheduleBuilder(@ModelAttribute("entry") Entry entry, Model model) {
        List<String> entryNameList = new ArrayList<>();
        for(Entry entry_in : entryService.getAllEntry()) {
            entryNameList.add(entry_in.getEntryName());
        }
        model.addAttribute("entryNameList",entryNameList);
        model.addAttribute("entry",entry);
        return "admin/scheduleConfig";
    }

    @RequestMapping(value = {"/schedule/sendevent"}, method = RequestMethod.POST)
    public String registerNewBlock(@RequestParam(name = "entryName") String entryName, Model model){
        System.out.println(entryName);
        long entryId = entryService.getEntryByEntryName(entryName).getEntryId();
        Set<Course> courseList = new HashSet<>(courseService.getAllCourse());
        Set<Block> blockList = new HashSet<>(blockService.getBlockByEntryName(entryName));
        scheduleBuilderService.runScheduleBuilder(entryId, courseList, blockList);

        String response = restService.getPostsPlainJSON();
        System.out.println(response);
        return "redirect:/schedule/builder";
    }



}
