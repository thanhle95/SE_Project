package edu.mum.mumsched.controller;

import edu.mum.mumsched.domain.Faculty;
import edu.mum.mumsched.service.FacultyService;
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
public class FacultyController {
    @Autowired
    FacultyService facultyService;

    @RequestMapping(value="/faculty/add", method= RequestMethod.GET)
    public String FacultyRegForm(@ModelAttribute("newFaculty") Faculty faculty, Model model){
        model.addAttribute("newFaculty", faculty);
        return "facultyRegForm";
    }

    @RequestMapping(value="/faculty", method= RequestMethod.GET)
    public String index(Model model) {
        List<Faculty> faculties = facultyService.getFaculty();

        model.addAttribute("facultyList", faculties);

        return "facultyList";
    }

    @RequestMapping("/faculty/update/{id}")
    public String updateFaculty(@PathVariable("id") long id, @Valid Faculty faculty, Model model) {

        faculty.setFacultyId(id);
        facultyService.save(faculty);
        model.addAttribute("faculties", facultyService.getFaculty());
        return "redirect:/faculty";

    }

    @RequestMapping("/faculty/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Faculty faculty = facultyService.getFacultyById(id);
        model.addAttribute("faculty", faculty);
        return "facultyUpdateForm";
    }

    @RequestMapping(value = {"/faculty/addnewfaculty"}, method = RequestMethod.POST)
    public String registerFaculty(@ModelAttribute("newFaculty") Faculty faculty, Model model){

        //STUDENT SAVED IN PERSISTENCE
        facultyService.save(faculty);

        return "redirect:/faculty";
    }

    @RequestMapping(value = "/faculty/delete/{id}", method = RequestMethod.GET)
    public String deleteFaculty(@PathVariable("id") Long facultyId, Model model) {
        facultyService.deleteById(facultyId);
        return "redirect:/faculty";
    }
}
