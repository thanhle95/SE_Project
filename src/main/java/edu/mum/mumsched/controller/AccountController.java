package edu.mum.mumsched.controller;

import edu.mum.mumsched.dao.RoleDao;
import edu.mum.mumsched.dao.UserDao;
import edu.mum.mumsched.domain.Entry;
import edu.mum.mumsched.domain.Role;
import edu.mum.mumsched.domain.Student;
import edu.mum.mumsched.domain.User;
import edu.mum.mumsched.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class AccountController {
    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    StudentService studentService;

    @Autowired
    FacultyService facultyService;

    @Autowired
    EntryService entryService;

    @RequestMapping("/secured")
    public void secureResource(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("accessing secured resource");
    }

    @RequestMapping(value="/login", method= RequestMethod.GET)
    public String LoginFormController(){
        return "login";
    }

    @GetMapping("/admin/account/add")
    public String accountAddForm(Model model) {

        List<String> entryNameList = new ArrayList<>();
        for(Entry entry : entryService.getAllEntry()) {
            entryNameList.add(entry.getEntryName());
        }
        model.addAttribute("user", new User());
        model.addAttribute("entryNameList",entryNameList);
        return "admin/accountAddForm";
    }

    @GetMapping("/admin/account/edit/{id}")
    public String accountAddForm(@PathVariable("id") Long id, Model model) {
        User user = userService.getUserByUserID(id);
        model.addAttribute("user", user);
        return "admin/accountUpdateForm";
    }

    @PostMapping(value = "/admin/account/update/{id}")
    public String accountAddForm(@PathVariable("id") Long id, @RequestParam(name = "user_role") String roleName, User user,Model model) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getRoleByRoleName(roleName));
        user.setRoles(roles);
        user.setUserId(id);
        user.setPassword(userService.getUserByUserID(id).getPassword());
        userService.save(user);
        return "redirect:/admin/account";
    }

    @PostMapping("/admin/process_register")
    public String processRegister(@RequestParam(name = "user_role") String roleName,
                                  @RequestParam(name = "user_entry") String entryName,
                                  User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setEnabled(true);
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getRoleByRoleName(roleName));
        user.setRoles(roles);
        if(roleName.equals("Student")){
            Student student = new Student();
            student.setUser(user);
            student.setEntry(entryService.getEntryByEntryName(entryName));
            studentService.save(student);
        }
        userService.save(user);

        return "redirect:/admin/account";
    }

    @RequestMapping(value = "/admin/account", method = RequestMethod.GET)
    public String accountListForm(Model model) {
        List<User> userList = new ArrayList<>();
        userList.addAll(userService.getAllUsers());

        model.addAttribute("userList",userList);
        return "admin/accountListForm";
    }

    @RequestMapping(value = {"/admin/account/delete/{id}"}, method=RequestMethod.GET)
    public String accountDeleteForm(@PathVariable("id") Long id, Model model) {
        for(Role role : userService.getUserByUserID(id).getRoles()){
            if(role.getName().equals("Student")){
                studentService.deleteById(studentService.getStudentByUserId(id).getStudentId());
            }
        }
        userService.deleteUserByUserID(id);
        return "redirect:/admin/account";
    }
}
