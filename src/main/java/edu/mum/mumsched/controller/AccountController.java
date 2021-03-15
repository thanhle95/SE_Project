package edu.mum.mumsched.controller;

import edu.mum.mumsched.dao.UserDao;
import edu.mum.mumsched.domain.ShopmeUserDetails;
import edu.mum.mumsched.domain.User;
import edu.mum.mumsched.service.BlockService;
import edu.mum.mumsched.service.imp.ShopmeUserDetailsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AccountController {
    @Autowired
    UserDao userDao;

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
    public String processRegister(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setEnabled(true);

        userDao.save(user);

        return "/";
    }
}
