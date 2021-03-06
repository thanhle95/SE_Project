package edu.mum.mumsched.controller;

import edu.mum.mumsched.domain.Entry;
import edu.mum.mumsched.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EntryController {
    @Autowired
    EntryService entryService;

    @RequestMapping(value = "/entry", method = RequestMethod.GET)
    public String entryListForm(@ModelAttribute("newEntry") Entry entry, Model model){
        List<Entry> entryList = new ArrayList<>();
        entryList.addAll(entryService.getAllEntry());
        System.out.println(entryList);
        model.addAttribute("entryList",entryList);
        model.addAttribute("newEntry",entry);
        return "admin/entryListForm";
    }

    @RequestMapping(value = "/entry/add", method = RequestMethod.GET)
    public String entryAddForm(@ModelAttribute("newEntry") Entry entry, Model model){
        model.addAttribute("newEntry",entry);
        return "admin/entryAddForm";
    }

    @RequestMapping(value = {"entry/addnewentry"}, method = RequestMethod.POST)
    public String registerNewEntry(@RequestParam(name = "entry_month") String month,
                                   @RequestParam(name = "entry_year") String year,
                                   @ModelAttribute("newEntry") Entry entry, Model model) {
        entry.setEntryName(month.substring(0,3) + year);
        entryService.save(entry);
        return "redirect:/entry";
    }

    @RequestMapping(value = {"entry/update/{id}"}, method = RequestMethod.POST)
    public String entryUpdateForm(@PathVariable("id") long id, Entry entry, Model model) {
        entry.setEntryId(id);
        entryService.save(entry);
        return "redirect:/entry";
    }

    @RequestMapping(value = {"entry/edit/{id}"}, method = RequestMethod.GET)
    public String entryEditForm(@PathVariable("id") long id, Model model) {
        Entry entry = entryService.getEntryByEntryID(id);
        model.addAttribute("editEntry",entry);
        return "admin/entryUpdateForm";
    }

    @RequestMapping(value = {"entry/delete/{id}"}, method = RequestMethod.GET)
    public String entryDeleteForm(@PathVariable("id") long id, Model model) {
        entryService.deleteEntryByEntryID(id);
        return "redirect:/entry";
    }
}
