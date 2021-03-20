package edu.mum.mumsched.controller;


import com.google.gson.Gson;
import edu.mum.mumsched.common.ScheduleStatus;
import edu.mum.mumsched.domain.*;
import edu.mum.mumsched.dto.ScheduleBuilderClient;
import edu.mum.mumsched.dto.SectionDTO;
import edu.mum.mumsched.service.*;
import edu.mum.mumsched.service.imp.RestService;
import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

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


        ScheduleBuilderClient scheduleBuilderClient = Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logger(new Slf4jLogger(ScheduleBuilderClient.class))
                .logLevel(Logger.Level.FULL)
                .target(ScheduleBuilderClient.class, "http://localhost:8001/api/section");


        System.out.println("===========Start==========================");

        System.out.println(scheduleBuilderClient);
        List<ScheduleJson> scheduleJsons = scheduleBuilderClient.findAll().stream()
                .map(SectionDTO::getScheduleJson)
                .collect(Collectors.toList());

        System.out.println(scheduleJsons.size());

        System.out.println("===========End===========================");

        String response = restService.getPostsPlainJSON();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        System.out.println(response);

        ScheduleJson[] scheduleJson = mapper.fromJson(response, ScheduleJson[].class);
        System.out.println(scheduleJson.length);
        generateSchedule(scheduleJson);
        return "redirect:/schedule/builder";
    }

    public void generateSchedule(ScheduleJson[] scheduleJson) {
        Schedule schedule = new Schedule();
        for(ScheduleJson sched : scheduleJson) {
            Session session = new Session();
            session.setCourse(courseService.getCourseByCourseID(sched.course_id));
            session.setStartDate(LocalDate.parse(sched.start_date,DateTimeFormatter.RFC_1123_DATE_TIME));
            session.setEndDate(LocalDate.parse(sched.end_date,DateTimeFormatter.RFC_1123_DATE_TIME));
            session.setSessionCapacity(courseService.getCourseByCourseID(sched.course_id).getCourseCapacity());
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
