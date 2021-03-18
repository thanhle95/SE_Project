package edu.mum.mumsched.controller;

import edu.mum.mumsched.domain.Course;
import edu.mum.mumsched.domain.Entry;
import edu.mum.mumsched.domain.Schedule;
import edu.mum.mumsched.service.EntryService;
import edu.mum.mumsched.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ScheduleController {
    @Autowired
    ScheduleService scheduleService;

    @Autowired
    EntryService entryService;

    @RequestMapping(value="/admin/schedule/select", method= RequestMethod.GET)
    public String scheduleSelect(@ModelAttribute("entry") Entry entry, Model model) {
        List<Schedule> scheduleList = scheduleService.getAllSchedule();
        List<String> entryNameList = new ArrayList<>();
        for(Schedule schedule : scheduleList) {
            entryNameList.add(schedule.getEntry().getEntryName());
        }
        model.addAttribute("entryNameList", entryNameList);
        model.addAttribute("entry", entry);
        return "/admin/scheduleSelectForm";
    }

    @RequestMapping(value="/admin/schedule", method= RequestMethod.GET)
    public String scheduleEntryView(@ModelAttribute("entry") Entry entry, Model model) {
        Entry dbEntry = entryService.getEntryByEntryName(entry.getEntryName());
        Schedule schedule = null;
        for(Schedule sc : dbEntry.getScheduleList()) {
            schedule = sc;
        }

        model.addAttribute("schedule", schedule);
        return "/admin/scheduleListForm";
    }
}
