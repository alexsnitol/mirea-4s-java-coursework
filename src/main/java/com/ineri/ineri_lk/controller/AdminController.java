package com.ineri.ineri_lk.controller;

import com.ineri.ineri_lk.model.Role;
import com.ineri.ineri_lk.model.User;
import com.ineri.ineri_lk.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;

/**
 * @author Kozlov Alexander
 */

@Controller
@RequestMapping("/users")
public class AdminController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private AuthController authController;

    @GetMapping
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView("view_users");
        List<User> users = userServiceImpl.getAll();

        mv = authController.setupUser(mv);
        mv.addObject("users", users);
        return mv;
    }

    @GetMapping("/new")
    public ModelAndView newUserForm() {
        ModelAndView mv = new ModelAndView("test_new_user");
        mv = authController.setupUser(mv);
        mv.addObject("user", new User());
        return mv;
    }

    @PostMapping("/new")
    public String createUser(User user) {
        userServiceImpl.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/{userId}/edit")
    public ModelAndView updateUserForm(@PathVariable("userId") Long id) {
        ModelAndView mv = new ModelAndView("edit_user");
        mv = authController.setupUser(mv);
        User user = userServiceImpl.getUserById(id);
        mv.addObject("user", user);
        mv.addObject("isAdmin", user.getRoles().contains(Role.ROLE_ADMIN));
        return mv;
    }

    @PostMapping("/{userId}/edit")
    public String updateUser(@PathVariable("userId") Long id,
                             User user,
                             @RequestParam(value = "isAdmin", required = false, defaultValue = "off")
                             String isAdmin) {
        user.getRoles().add(Role.ROLE_USER);
        if (isAdmin.equals("on"))
            user.getRoles().add(Role.ROLE_ADMIN);
        userServiceImpl.updateUser(user);
        return "redirect:/users";
    }

    @GetMapping("/{userId}/delete")
    public String deleteUser(@PathVariable("userId") Long id) {
        userServiceImpl.deleteById(id);
        return "redirect:/users";
    }

}
