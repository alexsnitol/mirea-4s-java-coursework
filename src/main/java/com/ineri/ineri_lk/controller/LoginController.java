package com.ineri.ineri_lk.controller;


import com.ineri.ineri_lk.model.User;
import com.ineri.ineri_lk.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Kozlov Alexander
 */

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String getLoginPage(User user, Model model) {
        model.addAttribute("user", user);
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") User user) {
        User userDB = userService.getUserByUsername(user.getUsername());
        if (userDB != null && user.getPassword().equals(userDB.getPassword()))
            return "redirect:/" + user.getUsername() + "/catalog";
        else
            return "redirect:/login";
    }
}
