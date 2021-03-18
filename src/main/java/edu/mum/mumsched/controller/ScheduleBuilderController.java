package edu.mum.mumsched.controller;


import com.google.gson.Gson;
import edu.mum.mumsched.common.ScheduleStatus;
import edu.mum.mumsched.domain.*;
import edu.mum.mumsched.service.*;
import edu.mum.mumsched.service.imp.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    @Autowired
    ScheduleService scheduleService;

    Gson mapper = new Gson();

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
        Set<Block> blockList = new HashSet<>(blockService.getBlockByEntryEntryId(entryId));
        scheduleBuilderService.runScheduleBuilder(entryId, courseList, blockList);

        String response = restService.getPostsPlainJSON();

        ScheduleJson[] scheduleJson = mapper.fromJson(response, ScheduleJson[].class);
        generateSchedule(scheduleJson);
        System.out.println(response);
        return "redirect:/schedule/builder";
    }

    public void generateSchedule(ScheduleJson[] scheduleJson) {
        Schedule schedule = new Schedule();
        for(ScheduleJson sched : scheduleJson) {
            Session session = new Session();
            session.setCourse(courseService.getCourseByCourseID(sched.course_id));
            session.setStartDate(LocalDate.parse(sched.start_date,DateTimeFormatter.RFC_1123_DATE_TIME));
            session.setEndDate(LocalDate.parse(sched.end_date,DateTimeFormatter.RFC_1123_DATE_TIME));
            Block block = blockService.getBlockByBlockID(sched.block_id);
            block.addSession(session);
            schedule.addBlock(block);
            schedule.setEntry(block.getEntry());
            schedule.setStatus(ScheduleStatus.DRAFT);
            block.getEntry().addSchedule(schedule);
        }
        entryService.save(schedule.getEntry());
        scheduleService.save(schedule);
    }



}
