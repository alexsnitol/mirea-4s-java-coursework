package com.ineri.ineri_lk.controller;

import com.ineri.ineri_lk.model.User;
import com.ineri.ineri_lk.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Kozlov Alexander
 */

@Controller
public class SignupController {

    @Autowired
    private UserService userService;

    @GetMapping("/signup")
    public String registration(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/signup")
    public String saveUser(@ModelAttribute("user") User user, Model model) {
        User userDB = userService.getUserByUsername(user.getUsername());

        if (!user.getPassword().equals(user.getConfirmPassword())) {
            model.addAttribute("passwordError", "Пароли не совпадают!");
            return "signup";
        } else {
            userService.saveUser(user);
            return "redirect:/catalog";
        }
    }
}
