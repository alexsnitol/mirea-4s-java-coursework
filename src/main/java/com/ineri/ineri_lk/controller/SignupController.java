package com.ineri.ineri_lk.controller;

import com.ineri.ineri_lk.model.ERole;
import com.ineri.ineri_lk.model.Role;
import com.ineri.ineri_lk.model.User;
import com.ineri.ineri_lk.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

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
        return "k-signup";
    }

    @PostMapping("/signup")
    public String saveUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "k-signup";
        }
        if (!user.getPassword().equals(user.getConfirmPassword())){
            model.addAttribute("passwordError", "Пароли не совпадают");
            return "k-signup";
        }
        Set<Role> roles = new HashSet<>();
        roles.add(new Role(ERole.USER));
        user.setRoles(roles);
        if (!userService.saveUser(user)){
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "k-signup";
        }

        return "redirect:/index";
    }
}
