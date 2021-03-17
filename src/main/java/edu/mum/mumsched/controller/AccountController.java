package edu.mum.mumsched.controller;

import edu.mum.mumsched.dao.RoleDao;
import edu.mum.mumsched.dao.UserDao;
import edu.mum.mumsched.domain.Role;
import edu.mum.mumsched.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

@Controller
public class AccountController {
    @Autowired
    UserDao userDAO;

    @Autowired
    RoleDao roleDAO;

    @RequestMapping("/secured")
    public void secureResource(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("accessing secured resource");
    }

    @RequestMapping(value="/login", method= RequestMethod.GET)
    public String LoginFormController(){
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());

        return "signup";
    }

    @PostMapping("/process_register")
    public String processRegister(@RequestParam(name = "user_role") String role, User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setEnabled(true);
        Set<Role> roles = new HashSet<>();
        roles.add(roleDAO.findRoleByName(role));
        user.setRoles(roles);

        userDAO.save(user);

        return "redirect:/";
    }
}
